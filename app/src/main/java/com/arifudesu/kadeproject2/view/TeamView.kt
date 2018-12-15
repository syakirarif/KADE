package com.arifudesu.kadeproject2.view

import com.arifudesu.kadeproject2.model.Player
import com.arifudesu.kadeproject2.model.Team

interface TeamView {
    fun listTeam(teams: List<Team>)
    fun listPlayer(players: List<Player>)
}