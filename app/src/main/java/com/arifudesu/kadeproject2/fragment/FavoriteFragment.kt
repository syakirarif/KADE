package com.arifudesu.kadeproject2.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.activity.DetailActivity
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.FavoriteAdapter
import com.arifudesu.kadeproject2.model.Favorite
import com.arifudesu.kadeproject2.db.database
import com.arifudesu.kadeproject2.view.FavoriteView
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.support.v4.intentFor

class FavoriteFragment : Fragment(), FavoriteView {

    private lateinit var adapter: FavoriteAdapter

    private var items: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteAdapter(context, items) {

            startActivity(
                intentFor<DetailActivity>(
                    "eventId" to "${it.eventId}",
                    "eventName" to "${it.eventName}",
                    "teamHomeId" to "${it.teamHomeId}",
                    "teamAwayId" to "${it.teamAwayId}"
                ).singleTop()
            )
        }

        swipe_favorite.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_favorite.isRefreshing = true

        rv_list_favorite.layoutManager = LinearLayoutManager(context)
        rv_list_favorite.adapter = adapter
        showFavorite()

        swipe_favorite.setOnRefreshListener {
            swipe_favorite.isRefreshing = true
            showFavorite()
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            items.clear()
            items.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipe_favorite.isRefreshing = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun showFavoriteList(data: List<Favorite>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
        swipe_favorite.isRefreshing = false
    }
}
