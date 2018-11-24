package com.arifudesu.kadeproject2.view

import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Team

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
interface DetailView {
    fun showBadgeTeamHome(badgeTeam: List<Team>)
    fun showBadgeTeamAway(badgeTeam: List<Team>)
    fun showEventDetail(data: List<Event>)
}