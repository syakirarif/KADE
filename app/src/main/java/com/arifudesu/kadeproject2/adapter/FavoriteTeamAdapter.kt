package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.activity.TeamDetailActivity
import com.arifudesu.kadeproject2.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_club.*
import org.jetbrains.anko.startActivity

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class FavoriteTeamAdapter (
    private val context: Context?,
    private val items: List<FavoriteTeam>
) : RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        FavoriteTeamViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_club,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: FavoriteTeamViewHolder, position: Int) {

        val item: FavoriteTeam = items[position]

        Picasso.get()
            .load(item.teamBadge?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(viewHolder.image)

        viewHolder.name.text = item.teamName

        viewHolder.itemView.setOnClickListener {
            context!!.startActivity<TeamDetailActivity>(
                "teamId" to item.teamId,
                "teamName" to item.teamName
            )
        }
    }

}

class FavoriteTeamViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

    var name: TextView = tv_list_club_name
    var image: ImageView = img_list_club_badge

}