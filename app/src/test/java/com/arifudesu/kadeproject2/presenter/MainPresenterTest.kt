package com.arifudesu.kadeproject2.presenter

import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.api.TheSportsDBApi
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.util.EventResponse
import com.arifudesu.kadeproject2.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
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

class MainPresenterTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }

    @Test
    fun testMainPresenter() {

        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        //val idLeague = "441613"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getPastEvents()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)


            presenter.getPastEventList()

            Mockito.verify(view).showEventList(events)
        }

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getNextEvents()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)


            presenter.getNextEventList()

            Mockito.verify(view).showEventList(events)
        }

    }
}