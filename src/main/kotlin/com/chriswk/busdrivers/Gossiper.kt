package com.chriswk.busdrivers

object Gossiper {

    fun gossip(drivers: List<BusDriver>): List<BusDriver> {
        drivers.groupBy { it.currentStop() }
            .filterValues { it.size > 1 }
            .forEach { (_, d) ->
                val driver = d[0]
                d.drop(1).forEach { otherDriver -> meet(driver, otherDriver) }
            }
        return drivers
    }
    fun meet(driver: BusDriver, driver2: BusDriver) {
        val newGossip = driver.gossip.union(driver2.gossip)
        driver.gossip = newGossip
        driver2.gossip = newGossip
    }

}