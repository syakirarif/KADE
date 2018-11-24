package com.arifudesu.kadeproject2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
@Parcelize
data class Favorite(
    val id: Long?,
    val eventId: String?,
    val eventName: String?,
    val teamHomeId: String?,
    val teamAwayId: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
    }
}