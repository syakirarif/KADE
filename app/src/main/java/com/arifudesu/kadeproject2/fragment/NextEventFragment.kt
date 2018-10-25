package com.arifudesu.kadeproject2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifudesu.kadeproject2.DetailActivity
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.EventAdapter
import com.arifudesu.kadeproject2.api.ApiRepository
import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.presenter.MainPresenter
import com.arifudesu.kadeproject2.view.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_event.*
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.support.v4.intentFor

/**
 * A simple [Fragment] subclass.
 *
 */
class NextEventFragment : Fragment(), MainView {

    private lateinit var adapter: EventAdapter
    private lateinit var presenter: MainPresenter

    private var items: MutableList<Event> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = EventAdapter(items, context) {

            startActivity(
                intentFor<DetailActivity>(
                    "event" to it
                ).singleTop()
            )

        }

        rv_list_next.layoutManager = LinearLayoutManager(context)
        rv_list_next.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_next_event, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getNextEventList()

        return view
    }

    override fun showEventList(data: List<Event>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
