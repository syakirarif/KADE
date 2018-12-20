package com.arifudesu.kadeproject2.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arifudesu.kadeproject2.fragment.MyPreferenceFragment

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, MyPreferenceFragment()).commit()

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
