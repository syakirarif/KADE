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
    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("idTeam")
    var teamId: String? = null
):Parcelable