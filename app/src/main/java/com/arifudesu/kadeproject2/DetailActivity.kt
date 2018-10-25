package com.arifudesu.kadeproject2

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.view.DetailView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import com.arifudesu.kadeproject2.util.DateConverter


class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter

    private val requestOptions: RequestOptions by lazy {
        RequestOptions()
            .placeholder(R.drawable.img_blank_badge)
            .transforms(CenterCrop())
    }

    private var items: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val event = intent.getParcelableExtra<Event>("event")

        supportActionBar?.title = event.eventName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)

        presenter.getTeamHomeDetail(event.teamHomeId)
        presenter.getTeamAwayDetail(event.teamAwayId)

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

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {
        Glide.with(this)
            .load(badgeTeam[0].teamBadge?.trim())
            .apply(requestOptions)
            .into(img_detail_badge_home)
    }

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {
        Glide.with(this)
            .load(badgeTeam[0].teamBadge?.trim())
            .apply(requestOptions)
            .into(img_detail_badge_away)
    }
}
