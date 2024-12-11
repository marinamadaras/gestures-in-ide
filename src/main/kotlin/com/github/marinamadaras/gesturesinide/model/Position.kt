package com.github.marinamadaras.gesturesinide.model

/**
 * Represents the position of an element on the screen.
 * @property x the x-coordinate of the element, default is -100 (i.e. outside the area of interest)
 * @property y the y-coordinate of the element, default is -100 (i.e. outside the area of interest)
 */
data class Position(var x: Int = -100, var y: Int = -100)
