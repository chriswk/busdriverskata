package com.chriswk.busdrivers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BusDriverTest {

    @Test
    fun `steps should work`() {
        var busDriver = BusDriver(route = listOf(1, 4, 5))
        assertThat(busDriver.currentStop()).isEqualTo(1)
        assertThat(busDriver.nextStop()).isEqualTo(4)
        busDriver = busDriver.tick()
        assertThat(busDriver.currentStop()).isEqualTo(4)
        assertThat(busDriver.nextStop()).isEqualTo(5)
        busDriver = busDriver.tick()
        assertThat(busDriver.currentStop()).isEqualTo(5)
        assertThat(busDriver.nextStop()).isEqualTo(1)
    }

    @Test
    fun `a Bus driver should know which gossip he knows`() {
        var busDriver = BusDriver(gossip = setOf(1))
        var busDriver2 = BusDriver(gossip = setOf(2))
        var busDriver3 = BusDriver(gossip = setOf(3))
        busDriver = Gossiper.meet(busDriver, busDriver2)
        busDriver2 = Gossiper.meet(busDriver2, busDriver)
        assertThat(busDriver.gossip).contains(1,2)
        assertThat(busDriver2.gossip).contains(1,2)
        busDriver = Gossiper.meet(busDriver, busDriver3)
        busDriver3 = Gossiper.meet(busDriver3, busDriver)
        assertThat(busDriver.gossip).contains(1,2,3)
        assertThat(busDriver2.gossip).contains(1,2)
        assertThat(busDriver3.gossip).contains(1,2,3)

    }
}