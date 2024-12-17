package com.github.marinamadaras.gesturesinide.handlers

import com.github.marinamadaras.gesturesinide.model.Position
import kotlin.math.abs

/**
 * Handler class that keeps track of the side and position from where the user's mouse entered the window.
 * It uses these values to calculate the distance from the entry side to the current mouse position
 */
class EntrySideHandler(
    var side: String = "",
    private var entryPosition: Position = Position()
) {

    /**
     * Method that detects the side from which the user's mouse entered the window
     *
     * @param x The x coordinate of the mouse position
     * @param y The y coordinate of the mouse position
     * @param width The width of the window
     * @param height The height of the window
     */
    fun detectEntrySide(x: Int, y: Int, width: Int, height: Int){
        val distanceToLeft = x
        val distanceToRight = width - x
        val distanceToTop = y
        val distanceToBottom = height - y

        side = when (minOf(distanceToLeft, distanceToRight, distanceToTop, distanceToBottom)) {
            distanceToLeft -> "LEFT"
            distanceToRight -> "RIGHT"
            distanceToTop -> "TOP"
            distanceToBottom -> "BOTTOM"
            else -> "UNKNOWN"
        }
        entryPosition = Position(x, y)
    }

    /**
     * Method that calculates the distance from the entry side to the current mouse position
     *
     * @param x The x coordinate of the mouse position
     * @param y The y coordinate of the mouse position
     */
    fun getDistanceBasedOnEntrySide(x: Int, y: Int): Int {
        return when (side) {
            "LEFT" -> abs(x - entryPosition.x)
            "RIGHT" -> abs(entryPosition.x - x)
            "TOP" -> abs(y - entryPosition.y)
            "BOTTOM" -> abs(entryPosition.y - y)
            else -> 0
        }
    }

    /**
     * Method that resets the side and entry position when the user's mouse leaves the window
     */
    fun reset() {
        side = ""
        entryPosition = Position()
    }
}
