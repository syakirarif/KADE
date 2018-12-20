package com.arifudesu.kadeproject2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arifudesu.kadeproject2.fragment.FavoriteEventFragment
import com.arifudesu.kadeproject2.fragment.FavoriteTeamFragment

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class TabFavAdapter (fm: FragmentManager) :
    FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> FavoriteEventFragment()
            else -> FavoriteTeamFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Event"
            else -> {
                return "Team"
            }
        }
    }
}