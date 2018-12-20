package com.arifudesu.kadeproject2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arifudesu.kadeproject2.fragment.NextEventFragment
import com.arifudesu.kadeproject2.fragment.PastEventFragment

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class MatchTabAdapter (fm: FragmentManager) :
    FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> PastEventFragment()
            else -> NextEventFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Past Match"
            else -> {
                return "Next Match"
            }
        }
    }
}