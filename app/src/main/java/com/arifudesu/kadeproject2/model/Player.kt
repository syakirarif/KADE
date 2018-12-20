package com.arifudesu.kadeproject2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

@Parcelize
data class Player(

    @SerializedName("idPlayer")
    val playerId: String? = null,

    @SerializedName("idTeam")
    val teamId: String? = null,

    @SerializedName("strNationality")
    val playerNationality: String? = null,

    @SerializedName("strPlayer")
    val playerName: String? = null,

    @SerializedName("strDescriptionEN")
    val playerDesc: String? = null,

    @SerializedName("strPosition")
    val playerPosition: String? = null,

    @SerializedName("strThumb")
    val playerThumb: String? = null,

    @SerializedName("strCutout")
    val playerPhoto: String? = null,

    @SerializedName("strTwitter")
    val playerTwitter: String? = null

) : Parcelable