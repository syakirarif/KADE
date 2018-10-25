package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.util.DetailResponse
import com.arifudesu.kadeproject2.view.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamHomeDetail(teamId: String?){
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeam(teamId)),
                DetailResponse::class.java
            )

            uiThread {
                view.showBadgeTeamHome(data.teams)
            }
        }
    }

    fun getTeamAwayDetail(teamId: String?){
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeam(teamId)),
                DetailResponse::class.java
            )

            uiThread {
                view.showBadgeTeamAway(data.teams)
            }
        }
    }
}