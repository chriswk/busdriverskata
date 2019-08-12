package com.chriswk.busdrivers

data class BusDriver(val currentIdx: Int = 0, val route: List<Int> = emptyList(), val gossip: Set<Int> = emptySet()) {

    fun tick(): BusDriver {
        return this.copy(currentIdx = nextIdx())
    }

    fun currentStop() = route[currentIdx]

    fun nextStop() = route[nextIdx()]

    private fun nextIdx(): Int = (currentIdx + 1) % route.size

    fun meet(otherDriver: BusDriver): BusDriver {
        return this.copy(gossip = gossip.union(otherDriver.gossip))
    }
}