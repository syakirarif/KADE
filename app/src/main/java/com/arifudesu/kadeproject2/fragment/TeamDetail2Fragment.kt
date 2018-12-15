package com.arifudesu.kadeproject2.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.PlayerAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.TeamPresenter
import com.arifudesu.kadeproject2.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_detail2.*

class TeamDetail2Fragment : Fragment(), TeamView {

    private var items: MutableList<Player> = mutableListOf()
    private lateinit var adapternya: PlayerAdapter
    private lateinit var presenter: TeamPresenter

    //private lateinit var rvPlayer: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapternya = PlayerAdapter(items, requireContext())

        //rvPlayer = findViewById(R.id.detail2_rv_list_player)
        detail2_rv_list_player.layoutManager = LinearLayoutManager(requireContext())
        detail2_rv_list_player.adapter = adapternya

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team_detail2, container, false)

        val data = arguments?.get("data") as Team
        Log.d("data.teamId: ", data.teamId)
        Log.d("data.teamName: ", data.teamName)

        /*adapternya = PlayerAdapter(items, requireContext())
        with(view.findViewById<RecyclerView>(R.id.detail2_rv_list_player)){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapternya
        }*/

        //rvPlayer = view.findViewById(R.id.detail2_rv_list_player)
        //rvPlayer.layoutManager = LinearLayoutManager(requireContext())
        //rvPlayer.adapter = adapternya

        val apiRepository = ApiRepository()
        val gson = Gson()

        presenter = TeamPresenter(this, apiRepository, gson)
        presenter.getPlayerList(data.teamId)

        return view
    }

    companion object {
        fun sendData(teams: Team) =
            TeamDetail2Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable("data", teams)
                }
            }
    }

    override fun listPlayer(players: List<Player>) {
        items.clear()
        //items.addAll(players)
        players.let { items.addAll(it) }
        adapternya.notifyDataSetChanged()

    }

    override fun listTeam(teams: List<Team>) {

    }



}
