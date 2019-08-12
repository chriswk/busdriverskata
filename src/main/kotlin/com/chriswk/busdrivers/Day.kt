package com.chriswk.busdrivers

class Day(val routes: List<List<Int>>, val maxStep: Int = 480) {
    var drivers = routes.mapIndexed { idx, route ->
        BusDriver(route = route, gossip = setOf(idx))
    }

    init {
        Gossiper.gossip(drivers)
    }

    fun findStop(): Minute {
        return (0..maxStep).fold(Minute(drivers = drivers)) { m, _ ->
            if (m.isAllGossipKnown()) { m } else m.nextMinute()
        }
    }
}