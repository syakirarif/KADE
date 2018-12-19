package com.arifudesu.kadeproject2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.FavoriteEvent
import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team
import com.arifudesu.kadeproject2.presenter.AppPresenter
import com.arifudesu.kadeproject2.presenter.DetailPresenter
import com.arifudesu.kadeproject2.presenter.DiscoverPresenter
import com.arifudesu.kadeproject2.util.DateConverter
import com.arifudesu.kadeproject2.view.AppView
import com.arifudesu.kadeproject2.view.DetailView
import com.arifudesu.kadeproject2.view.DiscoverView
import com.google.gson.Gson
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_player.*

class SearchPlayerSuggestionAdapter(
    private var items: List<Player>,
    inflater: LayoutInflater
) : SuggestionsAdapter<Player, SearchPlayerSuggestionAdapter.SuggestionHolder>(inflater) {

    //private var items: MutableList<Player> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SuggestionHolder {
        val view = layoutInflater.inflate(R.layout.list_player, parent, false)
        return SuggestionHolder(view)
    }

    override fun getSingleViewHeight(): Int {
        return 80;
    }

    override fun onBindSuggestionHolder(
        suggestion: Player,
        holder: SearchPlayerSuggestionAdapter.SuggestionHolder,
        position: Int
    ) {

        val item: Player = items[position]

        holder.name.text = item.playerName

        Picasso.get()
            .load(item.playerPhoto?.trim())
            .placeholder(R.drawable.img_blank_badge)
            .into(holder.image)

        //holder.bindItem(items[position])

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
                        if (item.playerName?.toLowerCase()!!.contains(term.toLowerCase())){
                            suggestions.add(item)
                            Log.d("suggestions", item.toString())
                        }
                }
                results.values = suggestions
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                suggestions = results!!.values as ArrayList<Player>
                notifyDataSetChanged()
            }

        }
    }

    class SuggestionHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        var image: CircleImageView = list_img_player_photo
        var name: TextView = list_tv_player_name
    }

}