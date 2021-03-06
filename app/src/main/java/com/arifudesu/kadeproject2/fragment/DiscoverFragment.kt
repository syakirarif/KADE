package com.arifudesu.kadeproject2.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.*
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.FavoriteEvent
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import com.jaredrummler.materialspinner.MaterialSpinner
import com.mancj.materialsearchbar.MaterialSearchBar

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class DiscoverFragment : Fragment(), AppView, MaterialSearchBar.OnSearchActionListener {

    private lateinit var spinnerEvent: MaterialSpinner

    private lateinit var searchBar: MaterialSearchBar

    private lateinit var searchEventSuggestion: SearchEventSuggestionAdapter

    private lateinit var presenter: AppPresenter

    private lateinit var adapterPlayer: PlayerAdapter
    private lateinit var adapterEvent: EventAdapter
    private lateinit var adapterTeam: TeamAdapter

    private lateinit var rvSearch: RecyclerView
    private lateinit var tvNotice: TextView

    private var itemsEvent: MutableList<Event> = mutableListOf()
    private var itemsPlayer: MutableList<Player> = mutableListOf()
    private var itemsTeam: MutableList<Team> = mutableListOf()

    private var isSearchPastEvent: Boolean = true
    private var isSearchNextEvent: Boolean = false
    private var isSearchPlayer: Boolean = false
    private var isSearchTeam: Boolean = false

    private var leagueId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discover, container, false)

        searchBar = view.findViewById(R.id.searchBar_discover)
        rvSearch = view.findViewById(R.id.rv_search_result)
        tvNotice = view.findViewById(R.id.discover_tv_notice)

        val inflater: LayoutInflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        searchEventSuggestion = SearchEventSuggestionAdapter(context, inflater)

        val pref = AppPreferences(context)
        leagueId = pref.getLeagueFavorite()

        val request = ApiRepository()
        val gson = Gson()
        presenter = AppPresenter(this, request, gson)

        spinnerEvent = view.findViewById(R.id.spinner_discover)

        spinnerEvent.setItems("Past Match", "Next Match", "Player", "Team")
        spinnerEvent.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<String> {
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: String?) {
                if (item == "Past Match") {
                    Log.d("masuk if1", item)

                    isSearchPastEvent = true
                    isSearchNextEvent = false
                    isSearchPlayer = false
                    isSearchTeam = false

                    presenter.getPastEventList(leagueId)
                    searchBarEvent()

                } else if (item == "Next Match") {
                    Log.d("masuk if2", item)

                    isSearchPastEvent = false
                    isSearchNextEvent = true
                    isSearchPlayer = false
                    isSearchTeam = false

                    presenter.getNextEventList(leagueId)
                    searchBarEvent()

                } else if (item == "Player") {
                    Log.d("masuk if3", item)

                    isSearchPastEvent = false
                    isSearchNextEvent = false
                    isSearchPlayer = true
                    isSearchTeam = false

                    searchBarPlayer()
                } else if (item == "Team") {

                    isSearchPastEvent = false
                    isSearchNextEvent = false
                    isSearchPlayer = false
                    isSearchTeam = true

                    searchBarTeam()

                }
            }

        })

        searchBar.setMaxSuggestionCount(2)
        searchBar.setSpeechMode(false)
        searchBar.setOnSearchActionListener(this)


        if (isSearchPastEvent) {
            presenter.getPastEventList(leagueId)
            searchBarEvent()
        }

        adapterEvent = EventAdapter(itemsEvent, context)
        adapterPlayer = PlayerAdapter(itemsPlayer, context)
        adapterTeam = TeamAdapter(itemsTeam, context)

        rvSearch.layoutManager = LinearLayoutManager(context)

        return view
    }

    fun searchBarPlayer() {
        itemsEvent.clear()
        searchBar.setHint("Enter Player Name")
        searchBar.setPlaceHolder("Discover Player")
    }

    fun searchBarEvent() {
        searchBar.setHint("Enter Event Name")
        searchBar.setPlaceHolder("Discover Event")
        searchEventSuggestion.suggestions = itemsEvent
        searchBar.setCustomSuggestionAdapter(searchEventSuggestion)
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {}

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

                Log.d("(EVENT) onTextChanged", searchBar.text)
                searchEventSuggestion.filter.filter(searchBar.text)
            }

        })
    }

    fun searchBarTeam() {
        itemsEvent.clear()
        searchBar.setHint("Enter Team Name")
        searchBar.setPlaceHolder("Discover Team")
    }

    override fun showEventList(data: List<Event>) {
        itemsEvent.clear()
        itemsEvent.addAll(data)
        searchEventSuggestion.notifyDataSetChanged()
        adapterEvent.notifyDataSetChanged()
    }

    override fun showPlayerList(list: List<Player>?) {
        itemsPlayer.clear()
        if (list != null) {
            list.let { itemsPlayer.addAll(it) }
            rvSearch.visibility = View.VISIBLE
            tvNotice.visibility = View.GONE
        } else {
            rvSearch.visibility = View.GONE
            tvNotice.visibility = View.VISIBLE
            tvNotice.text = resources.getString(R.string.result_not_found)
        }

        adapterPlayer.notifyDataSetChanged()
    }

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {}

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {}

    override fun showEventDetail(data: List<Event>) {}

    override fun showFavoriteList(data: List<FavoriteEvent>) {}

    override fun listPlayer(players: List<Player>?) {
        itemsPlayer.clear()
        if (players != null) {
            players.let { itemsPlayer.addAll(it) }
            rvSearch.visibility = View.VISIBLE
            tvNotice.visibility = View.GONE
        } else {
            rvSearch.visibility = View.GONE
            tvNotice.visibility = View.VISIBLE
            tvNotice.text = resources.getString(R.string.result_not_found)
        }
    }

    override fun listTeam(teams: List<Team>?) {
        itemsTeam.clear()
        if (teams != null) {
            itemsTeam.addAll(teams)
            rvSearch.visibility = View.VISIBLE
            tvNotice.visibility = View.GONE
        } else {
            rvSearch.visibility = View.GONE
            tvNotice.visibility = View.VISIBLE
            tvNotice.text = resources.getString(R.string.result_not_found)
        }
        adapterTeam.notifyDataSetChanged()

    }

    override fun onButtonClicked(buttonCode: Int) {}

    override fun onSearchStateChanged(enabled: Boolean) {}

    override fun onSearchConfirmed(text: CharSequence?) {

        val searchQuery: String = text.toString()
        //searchQuery = searchQuery.replace(" ", "%20")

        if (isSearchPlayer) {
            presenter.searchPlayer(searchQuery)
            rvSearch.adapter = adapterPlayer
        } else if (isSearchTeam) {
            presenter.searchTeamByName(searchQuery)
            rvSearch.adapter = adapterTeam
        } else {
            rvSearch.adapter = adapterEvent
        }
    }

}
