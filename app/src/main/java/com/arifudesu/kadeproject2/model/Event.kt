package com.arifudesu.kadeproject2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
@Parcelize
data class Event (
    @SerializedName("idEvent")
    var eventId: String = "",

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null,

    @SerializedName("intHomeScore")
    var scoreHome: Int? = null,

    @SerializedName("intAwayScore")
    var scoreAway: Int? = null,


    ////////////////// DETAIL //////////////////

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("strEvent")
    var eventName: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("idHomeTeam")
    var teamHomeId: String? = null,

    @SerializedName("idAwayTeam")
    var teamAwayId: String? = null,

    @SerializedName("strHomeGoalDetails")
    var goalHomeDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var goalAwayDetails: String? = null,

    //////// TEAM LINEUP  //////

    @SerializedName("strHomeLineupGoalkeeper")
    var lineupHomeGK: String? = null,

    @SerializedName("strHomeLineupDefense")
    var lineupHomeDEF: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var lineupHomeMID: String? = null,

    @SerializedName("strHomeLineupForward")
    var lineupHomeFWD: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var lineupAwayGK: String? = null,

    @SerializedName("strAwayLineupDefense")
    var lineupAwayDEF: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var lineupAwayMID: String? = null,

    @SerializedName("strAwayLineupForward")
    var lineupAwayFWD: String? = null,

    ////// CARDS ////

    @SerializedName("strAwayYellowCards")
    var cardYellowAway: String? = null,

    @SerializedName("strAwayRedCards")
    var cardRedAway: String? = null,

    @SerializedName("strHomeYellowCards")
    var cardYellowHome: String? = null,

    @SerializedName("strHomeRedCards")
    var cardRedHome: String? = null

):Parcelable