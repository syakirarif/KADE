package com.arifudesu.kadeproject2.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.TeamPresenter
import com.arifudesu.kadeproject2.view.TeamView
import com.google.gson.Gson

class TeamDetail1Fragment : Fragment() {

    //private lateinit var presenter: TeamPresenter
    //private lateinit var teams: Team

    private lateinit var tvName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_detail1, container, false)

        val data = arguments?.get("data") as Team

        tvName = view.findViewById(R.id.frag1_tv_name)
        tvName.text = data.teamName

        //val apiRepository = ApiRepository()
        //val gson = Gson()

        //presenter = TeamPresenter(this, apiRepository, gson)
        //presenter.getTeamDetail(data.teamId)

        return view
    }

    companion object {
        fun sendData(teams: Team) =
            TeamDetail1Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable("data", teams)
                }
            }
    }

}
