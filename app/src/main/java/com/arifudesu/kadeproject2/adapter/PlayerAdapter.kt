package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.activity.PlayerDetailActivity
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.TeamPresenter
import com.arifudesu.kadeproject2.util.AppPreferences
import com.arifudesu.kadeproject2.view.TeamView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_player.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(
    private val items: List<Player>,
    private val context: Context?
) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        PlayerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_player,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: PlayerViewHolder, position: Int) {

        val item: Player = items[position]

        Picasso.get()
            .load(item.playerPhoto?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(viewHolder.image)

        viewHolder.name.text = item.playerName

        //viewHolder.bindItem(items[position])

        viewHolder.itemView.setOnClickListener {
            /*context!!.startActivity<PlayerDetailActivity>("playerId" to items[position].playerId)
            val pref = AppPreferences(context)
            pref.setPlayerChoosen(items[position].playerId)*/
        }

    }

}

class PlayerViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    var image: CircleImageView = list_img_player_photo
    var name: TextView = list_tv_player_name

}