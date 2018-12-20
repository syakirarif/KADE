package com.arifudesu.kadeproject2.presenter

import android.content.Context
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.response.DetailResponse
import com.arifudesu.kadeproject2.response.EventResponse
import com.arifudesu.kadeproject2.response.PlayerResponse
import com.arifudesu.kadeproject2.response.TeamResponse
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class AppPresenter (
    private val view: AppView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    //val pref = AppPreferences(context)
    //val leagueId: String = pref.getLeagueFavorite()

    fun getPastEventList(leagueId: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPastEvents(leagueId)).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
        }
    }

    fun getNextEventList(leagueId: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getNextEvents(leagueId)).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
        }

    }

    fun getTeamHomeDetail(teamId: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeam(teamId)).await(),
                DetailResponse::class.java
            )

            view.showBadgeTeamHome(data.teams)
        }
    }

    fun getTeamAwayDetail(teamId: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeam(teamId)).await(),
                DetailResponse::class.java
            )

            view.showBadgeTeamAway(data.teams)
        }
    }

    fun getEventDetail(eventId: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getEventDetail(eventId)).await(),
                DetailResponse::class.java
            )

            view.showEventDetail(data.events)
        }
    }

    fun getPlayerList(teamId: String?){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayerList(teamId)).await(),
                PlayerResponse::class.java
            )

            view.listPlayer(data?.player)
        }
    }

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

    fun searchPlayer(playerName: String){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.searchPlayer(playerName)).await(),
                PlayerResponse::class.java
            )

            view.showPlayerList(data?.player)
        }
    }

    fun searchTeamByName(teamName: String){
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.searchTeamByName(teamName)).await(),
                TeamResponse::class.java
            )

            view.listTeam(data?.teams)
        }
    }

}