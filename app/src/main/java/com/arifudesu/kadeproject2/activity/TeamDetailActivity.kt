package com.arifudesu.kadeproject2.activity

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.R.drawable.ic_favorite
import com.arifudesu.kadeproject2.R.drawable.ic_favorite_border
import com.arifudesu.kadeproject2.R.id.add_to_favorite
import com.arifudesu.kadeproject2.R.menu.detail_menu
import com.arifudesu.kadeproject2.adapter.TabTeamDetailAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail2.*
import com.arifudesu.kadeproject2.db.database
import com.arifudesu.kadeproject2.model.*
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class TeamDetailActivity : AppCompatActivity(), AppView {

    private lateinit var team: Team
    //private lateinit var actionBar: ActionBar

    private lateinit var teamId: String
    private lateinit var teamName: String
    //private lateinit var leagueId: String

    private lateinit var tabs: TabLayout
    private lateinit var presenter: AppPresenter
    private lateinit var adapter: TabTeamDetailAdapter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail2)

        checkConnection()

        //val pref = AppPreferences(applicationContext)
        //leagueId = pref.getLeagueFavorite()

        val intent = intent

        //teams = intent.getParcelableExtra("data")
        teamId = intent.getStringExtra("teamId")
        teamName = intent.getStringExtra("teamName")

        title = teamName
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = AppPresenter(this, request, gson)
        presenter.getTeamDetail(teamId)


        tabs = findViewById(R.id.detail2_tab_layout)
        tabs.setupWithViewPager(detail2_team_viewPager)

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
        if (this::team.isInitialized) {
            try {
                database.use {
                    insert(
                        FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to team.teamId,
                        FavoriteTeam.TEAM_NAME to team.teamName,
                        FavoriteTeam.TEAM_BADGE to team.teamBadge
                    )
                    toast(getString(R.string.added_to_favorite))
                }
            } catch (e: SQLiteConstraintException) {
                Log.d("addToFavorite: ", e.localizedMessage)
            }
        } else {
            showAlertDialog("Warning", "Data wasn't loaded yet. Please wait.")
        }
    }

    private fun removeFromFavorite() {
        if (this::team.isInitialized) {
            try {
                database.use {
                    delete(
                        FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to teamId
                    )
                    toast(getString(R.string.removed_from_favorite))
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
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to teamId
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
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
        builder.setNeutralButton("OK", { dialog, id ->
            dialog.dismiss()
        })

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    override fun showPlayerList(list: List<Player>?) {}

    override fun listPlayer(players: List<Player>?) {}

    override fun listTeam(teams: List<Team>?) {

        if (teams != null) {
            team = Team(
                teams[0].teamId,
                teams[0].teamName,
                teams[0].teamNameAlternate,
                teams[0].teamBadge,
                teams[0].teamFormedYear,
                teams[0].teamLeague,
                teams[0].leagueId,
                teams[0].stadiumName,
                teams[0].stadiumThumb,
                teams[0].stadiumLocation,
                teams[0].teamDesc,
                teams[0].teamManager
            )

            Picasso.get()
                .load(teams[0].stadiumThumb?.trim())
                .into(detail2_img_stadium)

            Picasso.get()
                .load(teams[0].teamBadge?.trim())
                .placeholder(R.drawable.img_blank_badge)
                .into(detail2_img_logo)
        }

        adapter = TabTeamDetailAdapter(supportFragmentManager, team)
        detail2_team_viewPager.adapter = adapter

    }

    override fun showEventList(data: List<Event>) {}

    override fun showFavoriteList(data: List<FavoriteEvent>) {}

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {}

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {}

    override fun showEventDetail(data: List<Event>) {}
}
