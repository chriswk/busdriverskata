package com.chriswk.busdrivers

object Gossiper {

    fun gossip(drivers: List<BusDriver>): List<BusDriver> {
        drivers.groupBy { it.currentStop() }
            .filterValues { it.size > 1 }
            .forEach { (_, d) ->
                val driver = d.get(0)
                d.drop(1).forEach { otherDriver -> meet(driver, otherDriver) }
            }
        return drivers
    }
    fun meet(driver: BusDriver, driver2: BusDriver) {
        driver.gossip = driver.gossip.union(driver2.gossip)
        driver2.gossip = driver2.gossip.union(driver.gossip)
    }

}