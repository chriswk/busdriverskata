/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.chriswk.busdrivers

import java.io.File
import java.nio.charset.Charset

data class BusDriver(var currentIdx: Int = 0, val route: List<Int> = emptyList(), var gossip: Set<Int> = emptySet()) {

    fun tick(): BusDriver {
        return this.copy(currentIdx = nextIdx())
    }

    fun currentStop() = route[currentIdx]

    fun nextStop() = route[nextIdx()]

    private fun nextIdx(): Int = (currentIdx + 1) % route.size

}

fun main(args: Array<String>) {
    val routes = readRoutes(args[0])
    val day = Day(routes = routes)
    val stop = day.findStop()
    if (stop.isAllGossipKnown()) {
        println("All gossip shared after ${stop.currentMinute} stops")
    } else {
        println("Was never able to share all gossip")
    }
}

fun readRoutes(file: String): List<List<Int>> {
    val f = File(file)
    return f.readLines(Charset.forName("UTF-8"))
        .asSequence()
        .map { line ->
            line.split(" ")
                .map { station -> station.toInt() }
        }.toList()
}