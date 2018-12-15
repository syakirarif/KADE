package com.arifudesu.kadeproject2.util

import android.content.Context
import android.content.SharedPreferences
import com.arifudesu.kadeproject2.R

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AppPreferences(private val context: Context?) {

    private val KEY_LANGUAGE = "en"
    private val KEY_REGION = "us"

    private val KEY_LEAGUE = "league"
    private val KEY_CHOOSEN_TEAM_ID = "team"
    private val KEY_CHOOSEN_PLAYER_ID = "player"

    private val prefs: SharedPreferences

    fun setFirstRun(input: Boolean?){
        val editor = prefs.edit()
        val key = context!!.resources.getString(R.string.pref_movies)
        editor.putBoolean(key, input!!)
        editor.apply()
    }

    fun setTeamChoice(teamId: String?){
        val editor = prefs.edit()
        editor.putString(KEY_CHOOSEN_TEAM_ID, teamId)
        editor.apply()
    }

    fun getFirstRun(): Boolean {
        val key = context!!.resources.getString(R.string.pref_movies)
        return prefs.getBoolean(key, true)
    }

    fun setLeagueFavorite(leagueId: String?){
        val editor = prefs.edit()
        editor.putString(KEY_LEAGUE, leagueId)
        editor.apply()
    }

    fun setPlayerChoosen(playerId: String?){
        val editor = prefs.edit()
        editor.putString(KEY_CHOOSEN_PLAYER_ID, playerId)
        editor.apply()
    }

    fun getLeagueFavorite(): String {return prefs.getString(KEY_LEAGUE, null)}

    fun getTeamChoice(): String {return prefs.getString(KEY_CHOOSEN_TEAM_ID, null)}

    fun getPlayerChoosen(): String {return prefs.getString(KEY_CHOOSEN_PLAYER_ID, null)}

    init {
        val PREF_NAME = "movie_pref"
        prefs = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}