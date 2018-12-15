package com.arifudesu.kadeproject2.presenter

import android.content.Context
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.response.EventResponse
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: Context?
) {

    val pref = AppPreferences(context)
    val leagueId: String = pref.getLeagueFavorite()

    fun getPastEventList() {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPastEvents(leagueId)).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
        }
    }

    fun getNextEventList() {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getNextEvents(leagueId)).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
        }

    }
}