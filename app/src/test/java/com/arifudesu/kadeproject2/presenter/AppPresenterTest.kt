package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.response.DetailResponse
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */

class AppPresenterTest {

    @Mock
    private
    lateinit var view: AppView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: AppPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = AppPresenter(view, apiRepository, gson, null)
    }

    @Test
    fun testGetTeamHomeDetail() {

        val teams: MutableList<Team> = mutableListOf()
        val events: MutableList<Event> = mutableListOf()
        val response = DetailResponse(teams, events)
        val idTeamHome = "133604"
        val idTeamAway = "133619"
        val idEvent = "441613"


        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getTeam(idTeamHome)).await(),
                    DetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamHomeDetail(idTeamHome)

            Mockito.verify(view).showBadgeTeamHome(teams)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getTeam(idTeamAway)).await(),
                    DetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamHomeDetail(idTeamAway)

            Mockito.verify(view).showBadgeTeamAway(teams)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getEventDetail(idEvent)).await(),
                    DetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventDetail(idEvent)

            Mockito.verify(view).showEventDetail(events)
        }
    }


}