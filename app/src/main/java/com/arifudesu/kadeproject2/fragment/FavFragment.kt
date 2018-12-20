package com.arifudesu.kadeproject2.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.TabFavAdapter

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class FavFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fav, container, false)

        viewPager = view.findViewById(R.id.viewpager_fav)
        tabs = view.findViewById(R.id.tab_fav)

        val adapter = TabFavAdapter(childFragmentManager)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        return view
    }


}
