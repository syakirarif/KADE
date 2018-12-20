package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.model.League
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_league.view.*

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class LeagueAdapter(
    private val context: Context,
    private val items: List<League>,
    private val listener: (League) -> Unit

) : RecyclerView.Adapter<LeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        LeagueViewHolder(LayoutInflater.from(context).inflate(R.layout.list_league, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: LeagueViewHolder, position: Int) {
        viewHolder.bindItem(items[position], listener)
    }

}

class LeagueViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindItem(items: League, listener: (League) -> Unit) {
        //itemView.tv_list_league_name.text = items.leagueName
        Glide.with(itemView.context).load(items.leagueBadge).into(itemView.img_list_badge_league)
        itemView.setOnClickListener {
            listener(items)
        }
    }

}