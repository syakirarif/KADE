package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.*
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.util.DateConverter
import com.arifudesu.kadeproject2.view.AppView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_content_card.*
import kotlinx.android.synthetic.main.list_content_card.view.*

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class FavoriteAdapter(
    private val context: Context?,
    private val items: List<FavoriteEvent>,
    private val listener: (FavoriteEvent) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        FavoriteViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_content_card,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        viewHolder.bindItem(context, items[position], listener)
    }
}

class FavoriteViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer, AppView {

    private lateinit var presenter: AppPresenter

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {
        Picasso.get()
                .load(badgeTeam[0].teamBadge?.trim())
                .placeholder(R.drawable.img_blank_badge)
                .into(img_list_1badge)
    }

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {
        Picasso.get()
                .load(badgeTeam[0].teamBadge?.trim())
                .placeholder(R.drawable.img_blank_badge)
                .into(img_list_2badge)
    }

    override fun showEventDetail(data: List<Event>) {

        val items = Event(
            data[0].eventId,
            data[0].teamHome,
            data[0].teamAway,
            data[0].scoreHome,
            data[0].scoreAway,
            data[0].eventDate
        )

        itemView.tv_list_1name.text = items.teamHome
        itemView.tv_list_2name.text = items.teamAway

        if (items.scoreHome != null && items.scoreAway != null) {
            ll_skor.visibility = View.VISIBLE
            ll_batas.visibility = View.GONE
            itemView.tv_list_date.visibility = View.GONE
            itemView.tv_list_1skor.text = items.scoreHome?.toString()
            itemView.tv_list_2skor.text = items.scoreAway?.toString()
        } else {
            ll_batas.visibility = View.VISIBLE
            ll_skor.visibility = View.GONE
            itemView.tv_list_date.visibility = View.VISIBLE
            itemView.tv_list_date.text = DateConverter.getLongDate(items.eventDate)
        }

    }

    fun bindItem(context: Context?, items: FavoriteEvent, listener: (FavoriteEvent) -> Unit) {
        val request = ApiRepository()
        val gson = Gson()
        presenter = AppPresenter(this, request, gson, context)

        presenter.getTeamHomeDetail(items.teamHomeId)
        presenter.getTeamAwayDetail(items.teamAwayId)
        presenter.getEventDetail(items.eventId)

        itemView.setOnClickListener {
            listener(items)
        }
    }

    override fun showEventList(data: List<Event>) {}

    override fun showPlayerList(list: List<Player>?) {}

    override fun showFavoriteList(data: List<FavoriteEvent>) {}

    override fun listPlayer(players: List<Player>?) {}

    override fun listTeam(teams: List<Team>?) {}

}