package com.chriswk.busdrivers

data class Minute(val drivers: List<BusDriver>, var currentMinute: Int = 0) {
    fun nextMinute(): Minute {
        val ticked = drivers.map { it.tick() }
        val gossiped = Gossiper.gossip(ticked)
        return this.copy(drivers = gossiped, currentMinute = currentMinute + 1)
    }

    fun isAllGossipKnown(): Boolean {
        return drivers.all { it.gossip.size == drivers.size }
    }
}
