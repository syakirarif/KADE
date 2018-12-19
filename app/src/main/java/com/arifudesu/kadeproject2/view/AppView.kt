package com.arifudesu.kadeproject2.view

import com.arifudesu.kadeproject2.model.*

interface AppView {
    fun showEventList(data: List<Event>)
    fun showBadgeTeamHome(badgeTeam: List<Team>)
    fun showBadgeTeamAway(badgeTeam: List<Team>)
    fun showEventDetail(data: List<Event>)
    fun showPlayerList(list: List<Player>?)
    fun showFavoriteList(data: List<FavoriteEvent>)
    //fun showFavoriteTeamList(data: List<FavoriteTeam>)
    fun listPlayer(players: List<Player>?)
    fun listTeam(teams: List<Team>?)
}