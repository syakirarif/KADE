package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.util.DateConverter
import com.arifudesu.kadeproject2.view.DetailView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_view_past.*
import kotlinx.android.synthetic.main.list_view_past.view.*

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class EventAdapter(
    private val items: List<Event>,
    private val context: Context?,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        EventViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_view_past,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: EventViewHolder, p1: Int) {
        p0.bindItem(items[p1], listener)
    }
}

class EventViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer, DetailView {

    private lateinit var presenter: DetailPresenter

    val requestOptions: RequestOptions by lazy {
        RequestOptions()
            .placeholder(R.drawable.img_blank_badge)
            .transforms(CenterCrop())
    }

    override fun showBadgeTeamHome(badgeTeam: List<Team>) {
        Glide.with(containerView)
            .load(badgeTeam[0].teamBadge?.trim())
            .apply(requestOptions)
            .into(img_list_1badge)
    }

    override fun showBadgeTeamAway(badgeTeam: List<Team>) {
        Glide.with(containerView)
            .load(badgeTeam[0].teamBadge?.trim())
            .apply(requestOptions)
            .into(img_list_2badge)
    }

    fun bindItem(items: Event, listener: (Event) -> Unit) {

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)

        presenter.getTeamHomeDetail(items.teamHomeId)
        presenter.getTeamAwayDetail(items.teamAwayId)

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

        itemView.setOnClickListener {
            listener(items)
        }
    }

}