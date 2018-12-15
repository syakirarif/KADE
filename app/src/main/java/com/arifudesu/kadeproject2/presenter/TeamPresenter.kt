package com.arifudesu.kadeproject2.presenter

import android.content.Context
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.response.PlayerResponse
import com.arifudesu.kadeproject2.response.TeamResponse
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson

) {
    fun getClubList(leagueId: String?){
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeamList(leagueId)).await(),
                TeamResponse::class.java
            )

            view.listTeam(data.teams)
        }
    }

    fun getTeamDetail(teamId: String?){
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
                )

            view.listTeam(data.teams)
        }
    }

    fun getPlayerList(teamId: String?){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayerList(teamId)).await(),
                PlayerResponse::class.java
            )

            view.listPlayer(data.player)
        }
    }
}