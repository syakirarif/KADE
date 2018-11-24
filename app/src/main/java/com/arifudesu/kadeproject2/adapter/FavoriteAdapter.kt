package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Favorite
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.util.DateConverter
import com.arifudesu.kadeproject2.view.DetailView
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
    private val items: List<Favorite>,
    private val listener: (Favorite) -> Unit
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

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(items[p1], listener)
    }
}

class FavoriteViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer, DetailView {

    private lateinit var presenter: DetailPresenter

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

    fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)

        presenter.getTeamHomeDetail(items.teamHomeId)
        presenter.getTeamAwayDetail(items.teamAwayId)
        presenter.getEventDetail(items.eventId)

        itemView.setOnClickListener {
            listener(items)
        }
    }

}