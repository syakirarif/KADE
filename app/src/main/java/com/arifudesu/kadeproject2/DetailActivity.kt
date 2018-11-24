package com.arifudesu.kadeproject2

import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.view.DetailView
import com.arifudesu.kadeproject2.R.menu.detail_menu
import com.arifudesu.kadeproject2.R.id.add_to_favorite
import com.arifudesu.kadeproject2.R.drawable.ic_favorite
import com.arifudesu.kadeproject2.R.drawable.ic_favorite_border
import com.arifudesu.kadeproject2.model.Favorite
import com.arifudesu.kadeproject2.db.database
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import com.arifudesu.kadeproject2.util.DateConverter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var eventId: String
    private lateinit var eventName: String
    private lateinit var teamHomeId: String
    private lateinit var teamAwayId: String
    private lateinit var event: Event
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkConnection()

        val intent = intent

        //event = intent.getParcelableExtra("event")
        eventId = intent.getStringExtra("eventId")
        eventName = intent.getStringExtra("eventName")
        teamHomeId = intent.getStringExtra("teamHomeId")
        teamAwayId = intent.getStringExtra("teamAwayId")


        supportActionBar?.title = eventName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //eventId = event.eventId

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)

        presenter.getTeamHomeDetail(teamHomeId)
        presenter.getTeamAwayDetail(teamAwayId)
        presenter.getEventDetail(eventId)

        setContentView(R.layout.activity_detail)

    }

    private fun String.splitString(): String {
        val parts = split("(?<=;)")
        var result = ""

        for (index in parts.indices) {
            var temp = parts[index].replace("; ", "\n\n")
            temp = temp.replace(";", "\n\n")
            result += temp.trim()
        }

        return result
    }

    override fun showEventDetail(data: List<Event>) {

        event = Event(
            data[0].eventId,
            data[0].teamHome,
            data[0].teamAway,
            data[0].scoreHome,
            data[0].scoreAway,

            data[0].eventDate,
            data[0].eventName,
            data[0].leagueName,

            data[0].teamHomeId,
            data[0].teamAwayId,

            data[0].goalHomeDetails,
            data[0].goalAwayDetails,

            data[0].lineupHomeGK,
            data[0].lineupHomeDEF,
            data[0].lineupHomeMID,
            data[0].lineupHomeFWD,

            data[0].lineupAwayGK,
            data[0].lineupAwayDEF,
            data[0].lineupAwayMID,
            data[0].lineupAwayFWD,

            data[0].cardYellowAway,
            data[0].cardRedAway,
            data[0].cardYellowHome,
            data[0].cardRedHome
        )

        if (event.eventDate != null)
            tv_event_date.text = DateConverter.getLongDate(event.eventDate)

        tv_team_home.text = event.teamHome
        tv_team_away.text = event.teamAway
        tv_goal_home.text = event.goalHomeDetails?.splitString()
        tv_goal_away.text = event.goalAwayDetails?.splitString()

        //Log.d("goalHomeDetails: ", event.goalHomeDetails)
        //Log.d("goalAwayDetails: ", event.goalAwayDetails)

        val scoreHome: Int? = event.scoreHome
        val scoreAway: Int? = event.scoreAway

        fun String.toColor(): Int = Color.parseColor(this)

        if (scoreHome != null && scoreAway != null) {
            if (scoreHome > scoreAway) {
                tv_score_home.setTextColor("#ff7543".toColor())
            } else if (scoreHome < scoreAway) {
                tv_score_away.setTextColor("#ff7543".toColor())
            }
        }

        tv_score_home.text = event.scoreHome?.toString()
        tv_score_away.text = event.scoreAway?.toString()
        tv_league_name.text = event.leagueName

        tv_home_GK.text = event.lineupHomeGK?.splitString()
        tv_home_DF.text = event.lineupHomeDEF?.splitString()
        tv_home_MF.text = event.lineupHomeMID?.splitString()
        tv_home_FW.text = event.lineupHomeFWD?.splitString()

        tv_away_GK.text = event.lineupAwayGK?.splitString()
        tv_away_DF.text = event.lineupAwayDEF?.splitString()
        tv_away_MF.text = event.lineupAwayMID?.splitString()
        tv_away_FW.text = event.lineupAwayFWD?.splitString()

        tv_home_yellow.text = event.cardYellowHome?.splitString()
        tv_home_red.text = event.cardRedHome?.splitString()
        tv_away_yellow.text = event.cardYellowAway?.splitString()
        tv_away_red.text = event.cardRedAway?.splitString()

    }

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {
        Picasso.get()
            .load(badgeTeam[0].teamBadge?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(img_detail_badge_home)
    }

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {
        Picasso.get()
            .load(badgeTeam[0].teamBadge?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(img_detail_badge_away)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavoriteIcon()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        if (this::event.isInitialized) {
            try {
                database.use {
                    insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to event.eventId,
                        Favorite.EVENT_NAME to event.eventName,
                        Favorite.TEAM_HOME_ID to event.teamHomeId,
                        Favorite.TEAM_AWAY_ID to event.teamAwayId
                    )
                    toast("Added to Favorite")
                }
            } catch (e: SQLiteConstraintException) {
                Log.d("addToFavorite: ", e.localizedMessage)
            }
        } else {
            showAlertDialog("Warning", "Data wasn't loaded yet. Please wait.")
        }
    }

    private fun removeFromFavorite() {
        if (this::event.isInitialized) {
            try {
                database.use {
                    delete(
                        Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to eventId
                    )
                    toast("Removed from Favorite")
                }
            } catch (e: SQLiteConstraintException) {
                Log.d("removeFromFavorite: ", e.localizedMessage)
            }
        } else {
            showAlertDialog("Warning", "Data wasn't loaded yet. Please wait.")
        }

    }

    private fun setFavoriteIcon() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite_border)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to eventId
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun checkConnection() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            showAlertDialog("Warning", "No internet connection.\nPlease connect to any network and restart the app")
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton("OK", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
        })

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}
