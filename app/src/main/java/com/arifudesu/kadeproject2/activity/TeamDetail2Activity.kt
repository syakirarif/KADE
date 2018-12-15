package com.arifudesu.kadeproject2.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.TabTeamDetailAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.TeamPresenter
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.TeamView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail2.*

class TeamDetail2Activity : AppCompatActivity(), TeamView {

    private lateinit var presenter: TeamPresenter
    private lateinit var teams: Team

    private lateinit var teamId: String
    private lateinit var leagueId: String

    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail2)

        val pref = AppPreferences(applicationContext)
        leagueId = pref.getLeagueFavorite()

        val intent = intent

        teams = intent.getParcelableExtra("data")
        teamId = teams.teamId

        Picasso.get()
            .load(teams.stadiumThumb?.trim())
            .into(detail2_img_stadium)

        Picasso.get()
            .load(teams.teamBadge?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(detail2_img_logo)

        detail2_tv_name.text = teams.teamName
        detail2_tv_stadium.text = teams.stadiumName
        detail2_tv_year.text = teams.teamFormedYear

        val apiRepository = ApiRepository()
        val gson = Gson()

        presenter = TeamPresenter(this, apiRepository, gson)
        presenter.getTeamDetail(teamId)

        val adapter = TabTeamDetailAdapter(supportFragmentManager, teams)

        tabs = findViewById(R.id.detail2_tab_layout)

        detail2_team_viewPager.adapter = adapter
        tabs.setupWithViewPager(detail2_team_viewPager)

    }

    override fun listTeam(teams: List<Team>) {

    }
    override fun listPlayer(players: List<Player>) {

    }

}
