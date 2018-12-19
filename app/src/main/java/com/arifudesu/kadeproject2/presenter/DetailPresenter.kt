package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.response.DetailResponse
import com.arifudesu.kadeproject2.response.PlayerResponse
import com.arifudesu.kadeproject2.view.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

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

    fun searchPlayer(playerName: String){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.searchPlayer(playerName)).await(),
                PlayerResponse::class.java
            )

            view.listPlayer(data?.player)
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
}