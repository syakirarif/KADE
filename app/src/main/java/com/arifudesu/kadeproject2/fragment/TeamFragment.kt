package com.arifudesu.kadeproject2.fragment


import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.TeamAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.FavoriteEvent
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.util.ItemOffsetDecoration
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_club.*
import org.jetbrains.anko.support.v4.toast

class TeamFragment : Fragment(), AppView {

    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: AppPresenter

    private var items: MutableList<Team> = mutableListOf()

    private lateinit var leagueId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TeamAdapter(items, context)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_club.layoutManager = GridLayoutManager(context, 3)
        } else {
            rv_club.layoutManager = GridLayoutManager(context, 4)
        }

        rv_club.addItemDecoration(ItemOffsetDecoration(context!!, R.dimen.item_offset))
        rv_club.itemAnimator = DefaultItemAnimator()
        rv_club.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_club, container, false)

        val apiRepository = ApiRepository()
        val gson = Gson()

        val pref = AppPreferences(context)
        leagueId = pref.getLeagueFavorite()

        presenter = AppPresenter(this, apiRepository, gson, context)
        presenter.getClubList(leagueId)
        return view
    }

    override fun listTeam(teams: List<Team>?) {
        items.clear()
        //items.addAll(teams)
        if (teams != null)
            teams.let { items.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {}

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {}

    override fun showEventDetail(data: List<Event>) {}

    override fun showPlayerList(list: List<Player>?) {}

    override fun listPlayer(players: List<Player>?) {}

    override fun showEventList(data: List<Event>) {}

    override fun showFavoriteList(data: List<FavoriteEvent>) {}

}
