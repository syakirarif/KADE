package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.activity.DetailActivity
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.util.DateConverter
import com.arifudesu.kadeproject2.view.DetailView
import com.google.gson.Gson
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_content_card.*
import kotlinx.android.synthetic.main.list_content_card.view.*
import org.jetbrains.anko.startActivity

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class SearchEventSuggestionAdapter(
    private val context: Context?,
    inflater: LayoutInflater
) : SuggestionsAdapter<Event, SearchEventSuggestionAdapter.SuggestionHolder>(inflater) {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SuggestionHolder {
        val view = layoutInflater.inflate(R.layout.list_content_card, parent, false)
        return SuggestionHolder(view)
    }

    override fun getSingleViewHeight(): Int {
        return 80
    }

    override fun onBindSuggestionHolder(
        suggestion: Event,
        holder: SearchEventSuggestionAdapter.SuggestionHolder,
        position: Int
    ) {
        holder.bindItem(suggestion)
        holder.itemView.setOnClickListener {
            context!!.startActivity<DetailActivity>(
                "eventId" to suggestion.eventId,
                "eventName" to suggestion.eventName,
                "teamHomeId" to suggestion.teamHomeId,
                "teamAwayId" to suggestion.teamAwayId
            )
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = Filter.FilterResults()
                val term = constraint.toString()

                if (term.isEmpty())
                    suggestions = suggestions_clone
                else {
                    suggestions = ArrayList()
                    for (item in suggestions_clone)
                        if (
                            item.teamHome?.toLowerCase()!!.contains(term.toLowerCase()) ||
                            item.teamAway?.toLowerCase()!!.contains(term.toLowerCase())
                        )
                            suggestions.add(item)
                }
                results.values = suggestions
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                suggestions = results!!.values as ArrayList<Event>
                notifyDataSetChanged()
            }

        }
    }

    class SuggestionHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, DetailView {

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

        override fun listPlayer(players: List<Player>?) {}

        fun bindItem(items: Event) {
            val request = ApiRepository()
            val gson = Gson()
            presenter = DetailPresenter(this, request, gson)

            presenter.getTeamHomeDetail(items.teamHomeId)
            presenter.getTeamAwayDetail(items.teamAwayId)
            presenter.getEventDetail(items.eventId)


        }

    }

}