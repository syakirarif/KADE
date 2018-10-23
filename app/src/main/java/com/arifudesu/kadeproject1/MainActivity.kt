package com.arifudesu.kadeproject1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class MainActivity : AppCompatActivity() {

    private var items: MutableList<ClubModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        ///// Kotlin Android Extension ///////
        rv_club.layoutManager = LinearLayoutManager(this)
        rv_club.adapter = ClubAdapter(this, items) {

            //////// ANKO COMMON //////////
            startActivity(
                intentFor<DetailActivity>(
                    "detail" to it.desc,
                    "logo" to it.image,
                    "nama" to it.name
                ).singleTop()
            )

        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_detail)

        items.clear()

        for (i in name.indices) {
            items.add(ClubModel(name[i], image.getResourceId(i, 0), desc[i]))
        }

        image.recycle()
    }
}
