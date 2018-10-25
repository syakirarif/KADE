package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.util.EventResponse
import com.arifudesu.kadeproject2.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPastEventList(){
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPastEvents()),
                EventResponse::class.java
            )

            uiThread {
                view.showEventList(data.events)
            }
        }
    }

    fun getNextEventList(){
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getNextEvents()),
                EventResponse::class.java
            )

            uiThread {
                view.showEventList(data.events)
            }
        }
    }
}