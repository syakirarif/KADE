package com.arifudesu.kadeproject2.activity

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.LeagueAdapter
import com.arifudesu.kadeproject2.model.League
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.util.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_setup.*
import org.jetbrains.anko.*

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class SetupActivity : AppCompatActivity() {

    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val pref = AppPreferences(this)

        initData()

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_list_league.layoutManager = GridLayoutManager(this, 2)
        } else {
            rv_list_league.layoutManager = GridLayoutManager(this, 4)
        }

        rv_list_league.addItemDecoration(ItemOffsetDecoration(this, R.dimen.item_offset))
        rv_list_league.itemAnimator = DefaultItemAnimator()

        rv_list_league.adapter = LeagueAdapter(this, items) {

            toast(it.leagueName.toString())

            pref.setLeagueFavorite(it.leagueId)
            pref.setFirstRun(false)
            startActivity(intentFor<MainActivity>().noHistory())

        }


    }

    private fun initData() {
        val name = resources.getStringArray(R.array.arr_league_name)
        val id = resources.getStringArray(R.array.arr_league_id)
        val img = resources.obtainTypedArray(R.array.arr_league_image)

        items.clear()

        for (i in name.indices) {
            items.add(League(id[i], name[i], img.getResourceId(i, 0)))
        }

    }
}