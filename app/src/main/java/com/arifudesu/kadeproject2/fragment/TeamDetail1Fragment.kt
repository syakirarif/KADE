package com.arifudesu.kadeproject2.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.model.Team
import kotlinx.android.synthetic.main.fragment_team_detail1.view.*

class TeamDetail1Fragment : Fragment() {

    //private lateinit var tvName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_detail1, container, false)

        val data = arguments?.get("data") as Team

        //tvName = view.findViewById(R.id.frag1_tv_name)

        view.frag1_tv_name.text = data.teamNameAlternate
        view.frag1_tv_stadium.text = data.stadiumName
        view.frag1_tv_stadium_location.text = data.stadiumLocation
        view.frag1_tv_year.text = data.teamFormedYear
        view.frag1_tv_desc.text = data.teamDesc

        return view
    }

    companion object {
        fun sendData(teams: Team) =
            TeamDetail1Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable("data", teams)
                }
            }
    }

}
