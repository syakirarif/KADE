package com.arifudesu.kadeproject2.activity

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.fragment.*
import com.arifudesu.kadeproject2.util.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class MainActivity : AppCompatActivity() {

    private lateinit var pref: AppPreferences

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
                val fragment = FavFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.btm_discover -> {
                val fragment = DiscoverFragment()
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
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        pref = AppPreferences(this)

        val isFirstRun: Boolean = pref.getFirstRun()

        if (isFirstRun) {
            startActivity(intentFor<SetupActivity>().singleTop())
        } else {
            checkConnection()

            toolbar.inflateMenu(R.menu.main_menu)
            toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    if (item!!.itemId == R.id.menu_settings) {
                        startActivity<SettingsActivity>()
                        return true
                    }

                    return false
                }
            })

            btm_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            val fragment = MatchFragment()
            addFragment(fragment)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun checkConnection() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            showAlertDialog("Warning", "No internet connection.\nPlease connect to any network and restart the app")
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton("OK", { dialog, id ->
            dialog.dismiss()
        })

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}
