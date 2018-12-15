package com.arifudesu.kadeproject2.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.FrameLayout
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.fragment.TeamFragment
import com.arifudesu.kadeproject2.fragment.FavoriteFragment
import com.arifudesu.kadeproject2.fragment.MatchFragment
import com.arifudesu.kadeproject2.util.AppPreferences
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class Main2Activity : AppCompatActivity() {

    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.btm_match -> {
                val fragment = MatchFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.btm_club -> {
                val fragment = TeamFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.btm_favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content_match, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val pref = AppPreferences(this)

        val isFirstRun: Boolean = pref.getFirstRun()

        if (isFirstRun) {
            startActivity(intentFor<SetupActivity>().singleTop())
        } else {
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            val fragment = MatchFragment()
            addFragment(fragment)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
