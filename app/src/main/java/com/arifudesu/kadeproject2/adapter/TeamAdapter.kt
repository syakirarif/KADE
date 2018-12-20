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
import com.arifudesu.kadeproject2.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_club.*
import org.jetbrains.anko.startActivity

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class TeamAdapter(
    private val items: List<Team>,
    private val context: Context?
) : RecyclerView.Adapter<ClubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        ClubViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_club,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: ClubViewHolder, position: Int) {

        val item: Team = items[position]

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
            //context!!.startActivity<TeamDetailActivity>("data" to item)
            /*val pref = AppPreferences(context)
            pref.setTeamChoice(items[position].teamId)*/

        }
    }

}

class ClubViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    var name: TextView = tv_list_club_name
    var image: ImageView = img_list_club_badge

}