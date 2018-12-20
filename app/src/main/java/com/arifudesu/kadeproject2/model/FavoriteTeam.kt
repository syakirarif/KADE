package com.arifudesu.kadeproject2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

@Parcelize
data class FavoriteTeam(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TB_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}