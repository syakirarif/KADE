package com.arifudesu.kadeproject2.fragment


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.FavoriteTeamAdapter
import com.arifudesu.kadeproject2.model.FavoriteTeam
import com.arifudesu.kadeproject2.util.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import com.arifudesu.kadeproject2.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class FavoriteTeamFragment : Fragment() {

    private lateinit var adapter: FavoriteTeamAdapter

    private var items: MutableList<FavoriteTeam> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(context, items)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_team_favorite.layoutManager = GridLayoutManager(context, 3)
        } else {
            rv_team_favorite.layoutManager = GridLayoutManager(context, 4)
        }

        swipe_favorite_team.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_favorite_team.isRefreshing = true

        rv_team_favorite.addItemDecoration(ItemOffsetDecoration(context!!, R.dimen.item_offset))
        rv_team_favorite.itemAnimator = DefaultItemAnimator()
        rv_team_favorite.adapter = adapter

        showFavorite()

        swipe_favorite_team.setOnRefreshListener {
            swipe_favorite_team.isRefreshing = true
            showFavorite()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            items.clear()
            items.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipe_favorite_team.isRefreshing = false
        }
    }


}
