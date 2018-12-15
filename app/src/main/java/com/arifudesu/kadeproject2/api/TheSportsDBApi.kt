package com.arifudesu.kadeproject2.api

import android.net.Uri
import android.util.Log
import com.arifudesu.kadeproject2.BuildConfig

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
object TheSportsDBApi {
    fun getPastEvents(leagueId: String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    fun getNextEvents(leagueId: String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    fun getEventDetail(eventId: String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", eventId)
            .build()
            .toString()
    }

    fun getTeam(teamId: String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }

    fun getTeamList(leagueId: String?) : String{
        val uri = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()

        Log.d("URI getTeamList", uri)
        return uri
    }

    fun getTeamDetail(teamId: String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }

    fun getPlayerList(teamId: String?) : String{
        val uri = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()

        Log.d("URI getPlayerList", uri)
        return uri
    }

    fun getPlayerDetail(playerId: String?) : String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupplayer.php")
            .appendQueryParameter("id", playerId)
            .build()
            .toString()
    }

}