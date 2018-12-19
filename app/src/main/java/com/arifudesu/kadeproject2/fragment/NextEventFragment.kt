package com.arifudesu.kadeproject2.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.activity.DetailActivity
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.EventAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.FavoriteEvent
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_event.*
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.support.v4.intentFor


class NextEventFragment : Fragment(), AppView {

    private lateinit var adapter: EventAdapter
    private lateinit var presenter: AppPresenter

    private var items: MutableList<Event> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = EventAdapter(items, context)
        swipe_next_event.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_next_event.isRefreshing = true

        rv_list_next.layoutManager = LinearLayoutManager(context)
        rv_list_next.adapter = adapter

        swipe_next_event.setOnRefreshListener {
            swipe_next_event.isRefreshing = true
            presenter.getNextEventList()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_next_event, container, false)

        val pref = AppPreferences(context)
        pref.setPastFragment(false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = AppPresenter(this, request, gson, this.context)
        presenter.getNextEventList()

        return view
    }

    override fun showEventList(data: List<Event>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
        if (swipe_next_event != null)
            swipe_next_event.isRefreshing = false
    }

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {}

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {}

    override fun showEventDetail(data: List<Event>) {}

    override fun showPlayerList(list: List<Player>?) {}

    override fun showFavoriteList(data: List<FavoriteEvent>) {}

    override fun listPlayer(players: List<Player>?) {}

    override fun listTeam(teams: List<Team>?) {}


}
