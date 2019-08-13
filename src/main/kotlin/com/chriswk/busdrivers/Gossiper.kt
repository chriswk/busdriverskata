package com.chriswk.busdrivers

object Gossiper {

    fun gossip(drivers: List<BusDriver>): List<BusDriver> {
        return drivers.map { d ->
            drivers.filter { it.currentStop() == d.currentStop() && it != d }.fold(d) { updated, met ->
                updated.meet(met)
            }
        }
    }
}
