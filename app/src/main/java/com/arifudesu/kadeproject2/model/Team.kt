package com.arifudesu.kadeproject2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
@Parcelize
data class Team (

    @SerializedName("idTeam")
    var teamId: String,

    @SerializedName("strTeam")
    var teamName: String,

    @SerializedName("strAlternate")
    var teamNameAlternate: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strLeague")
    var teamLeague: String,

    @SerializedName("idLeague")
    var leagueId: String,

    @SerializedName("strStadium")
    var stadiumName: String? = null,

    @SerializedName("strStadiumThumb")
    var stadiumThumb: String? = null,

    @SerializedName("strStadiumLocation")
    var stadiumLocation: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDesc: String,

    @SerializedName("strManager")
    var teamManager: String


):Parcelable