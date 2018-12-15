package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.response.PlayerResponse
import com.arifudesu.kadeproject2.view.PlayerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPlayerList(teamId: String?){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayerList(teamId)).await(),
                PlayerResponse::class.java
            )

            view.listPlayer(data?.player)
        }
    }

    fun getPlayerDetail(playerId: String?){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayerDetail(playerId)).await(),
                PlayerResponse::class.java
            )

            view.listPlayer(data?.player)
        }
    }
}