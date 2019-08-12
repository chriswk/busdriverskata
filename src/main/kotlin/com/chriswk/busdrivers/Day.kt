package com.chriswk.busdrivers

class Day(val routes: List<List<Int>>, val maxStep: Int = 480) {
    val drivers = routes.mapIndexed { idx, route ->
        BusDriver(route = route, gossip = setOf(idx))
    }

    fun findStop(): Minute {
        val gossipedAtStart = Gossiper.gossip(drivers)
        val s = generateSequence(Minute(drivers = gossipedAtStart)) { m -> m.nextMinute() }
        return s.dropWhile { !it.isAllGossipKnown() && it.currentMinute < maxStep }.first()
    }
}