package com.arifudesu.kadeproject2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arifudesu.kadeproject2.fragment.FavoriteFragment
import com.arifudesu.kadeproject2.fragment.NextEventFragment
import com.arifudesu.kadeproject2.fragment.PastEventFragment

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> PastEventFragment()
            1 -> NextEventFragment()
            else -> FavoriteFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}