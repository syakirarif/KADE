package com.arifudesu.kadeproject1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class ClubAdapter(
    private val context: Context,
    private val items: List<ClubModel>,
    private val listener: (ClubModel) -> Unit
) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(items[p1], listener)
    }

    ///// Kotlin Android Extension //////
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(items: ClubModel, listener: (ClubModel) -> Unit) {
            itemView.list_name.text = items.name

            Glide.with(itemView.context).load(items.image).into(itemView.list_image)
            itemView.setOnClickListener {
                listener(items)
            }
        }

    }
}