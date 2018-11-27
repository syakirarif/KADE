package com.arifudesu.kadeproject2.activity

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.adapter.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("No internet connection.\nPlease connect to any network and restart the app")
            builder.setTitle("Warning")
            builder.setNeutralButton("OK", DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        configureTabLayout()

    }

    private fun configureTabLayout() {

        tab_layout.addTab(tab_layout.newTab().setText("Past Event"))
        tab_layout.addTab(tab_layout.newTab().setText("Next Event"))
        tab_layout.addTab(tab_layout.newTab().setText("Favorite"))

        val adapter = TabPagerAdapter(
                supportFragmentManager,
                tab_layout.tabCount
        )
        pager.adapter = adapter

        pager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        )
        tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

}
