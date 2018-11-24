package com.arifudesu.kadeproject2.util

import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
data class DetailResponse(
    val teams: List<Team>,
    val events: List<Event>
)