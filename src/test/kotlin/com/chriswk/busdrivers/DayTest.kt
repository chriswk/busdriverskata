package com.chriswk.busdrivers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DayTest {

    @Test
    fun `two drivers with just common stops should have all gossip`() {
        val d = Day(routes = listOf(listOf(1), listOf(1)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isTrue()
        assertThat(m.currentMinute).isEqualTo(0)
    }

    @Test
    fun `drivers with a common stop after three ticks`() {
        val d = Day(routes = listOf(listOf(1, 2, 3), listOf(2, 1, 3)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isTrue()
        assertThat(m.currentMinute).isEqualTo(2)
    }

    @Test
    fun `drivers with no common stops should return null`() {
        val d = Day(routes = listOf(listOf(1, 2), listOf(3, 4)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isFalse()
    }

    @Test
    fun `Example 1 from kata`() {
        val d = Day(routes = listOf(listOf(3,1,2,3), listOf(3,2,3,1), listOf(4,2,3,4,5)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isTrue()
        assertThat(m.currentMinute).isEqualTo(1)
    }

    @Test
    fun `Example 2 from kata`() {
        val d = Day(routes = listOf(listOf(2,1,2), listOf(5,2,8)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isFalse()
    }

    @Test
    fun `Found after a long loop`() {
        val d = Day(routes = listOf(listOf(1,2,3), listOf(4,5,6,7,8,9,3)))
        val m = d.findStop()
        assertThat(m.isAllGossipKnown()).isTrue()
        assertThat(m.currentMinute).isEqualTo(20)
    }

}