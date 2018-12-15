package com.arifudesu.kadeproject2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arifudesu.kadeproject2.fragment.TeamDetail1Fragment
import com.arifudesu.kadeproject2.fragment.TeamDetail2Fragment
import com.arifudesu.kadeproject2.model.Team

class TabTeamDetailAdapter(fm: FragmentManager, private val teams: Team) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> TeamDetail1Fragment.sendData(teams)
            else -> TeamDetail2Fragment.sendData(teams)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position){
            0 -> "INFO"
            else -> {
                return "PLAYERS"
            }
        }
    }
}