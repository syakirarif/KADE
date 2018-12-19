package com.arifudesu.kadeproject2.view

import com.arifudesu.kadeproject2.model.Event
import com.arifudesu.kadeproject2.model.Player

interface DiscoverView {
    fun showEventList(data: List<Event>)
    fun showPlayerList(players: List<Player>)
}