package com.chriswk.busdrivers

object Gossiper {

    fun gossip(drivers: List<BusDriver>): List<BusDriver> {
        return drivers.map { d ->
            drivers.filter { it.currentStop() == d.currentStop() && it != d }.fold(d) { updated, met ->
                meet(updated, met)
            }
        }
    }

    fun meet(driver: BusDriver, met: BusDriver): BusDriver {
        val newGossip = driver.gossip.union(met.gossip)
        return driver.copy(gossip = newGossip)
    }

}