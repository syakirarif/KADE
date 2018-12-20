package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.response.DetailResponse
import com.arifudesu.kadeproject2.response.EventResponse
import com.arifudesu.kadeproject2.response.PlayerResponse
import com.arifudesu.kadeproject2.response.TeamResponse
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
        //val context: Context = mock(Context::class.java)
        presenter = AppPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetTeamHomeDetail() {

        val teams: MutableList<Team> = mutableListOf()
        val events: MutableList<Event> = mutableListOf()
        val players: MutableList<Player> = mutableListOf()

        val responseDetail = DetailResponse(teams, events)
        val responseEvent = EventResponse(events)
        val responsePlayer = PlayerResponse(players)
        val responseTeam = TeamResponse(teams)

        val idTeamHome = "133604"
        val idTeamAway = "133619"
        val idEvent = "441613"
        val idLeague = "4328"
        val namePlayer = "messi"
        val nameTeam = "chelsea"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getPastEvents(idLeague)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(responseEvent)

            presenter.getPastEventList(idLeague)

            Mockito.verify(view).showEventList(events)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getNextEvents(idLeague)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(responseEvent)

            presenter.getNextEventList(idLeague)

            Mockito.verify(view).showEventList(events)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getTeam(idTeamHome)).await(),
                    DetailResponse::class.java
                )
            ).thenReturn(responseDetail)

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
            ).thenReturn(responseDetail)

            presenter.getTeamAwayDetail(idTeamAway)

            Mockito.verify(view).showBadgeTeamAway(teams)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getEventDetail(idEvent)).await(),
                    DetailResponse::class.java
                )
            ).thenReturn(responseDetail)

            presenter.getEventDetail(idEvent)

            Mockito.verify(view).showEventDetail(events)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getPlayerList(idTeamHome)).await(),
                    PlayerResponse::class.java
                )
            ).thenReturn(responsePlayer)

            presenter.getPlayerList(idTeamHome)

            Mockito.verify(view).listPlayer(players)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getTeamList(idLeague)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.getClubList(idLeague)

            Mockito.verify(view).listTeam(teams)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.getTeamDetail(idTeamHome)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.getTeamDetail(idTeamHome)

            Mockito.verify(view).listTeam(teams)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.searchPlayer(namePlayer)).await(),
                    PlayerResponse::class.java
                )
            ).thenReturn(responsePlayer)

            presenter.searchPlayer(namePlayer)

            Mockito.verify(view).showPlayerList(players)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDBApi.searchTeamByName(nameTeam)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.searchTeamByName(nameTeam)

            Mockito.verify(view).listTeam(teams)
        }
    }


}