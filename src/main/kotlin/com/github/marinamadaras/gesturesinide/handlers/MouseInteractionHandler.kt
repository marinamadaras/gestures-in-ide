package com.github.marinamadaras.gesturesinide.handlers

import com.github.marinamadaras.gesturesinide.visuals.UIConstants
import com.github.marinamadaras.gesturesinide.model.Position
import com.github.marinamadaras.gesturesinide.model.Size
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import java.awt.Dimension

/**
 * The listeners delegate the logic for handling **mouse interactions** with the components
 * to this class. The way the component is updated depends on the side of the window
 * from which the mouse enters and the direction in which the mouse moves.
 *
 * The rules for updating the component are as follows:
 * - The component is scaled based on the distance the mouse moves from the direction of the entry side
 * - The component is moved to the position of the mouse
 *
 * @param component the component that is being interacted with and needs to be updated
 * @param interactionBounds the bounds of the area where the interaction is happening (i.e. the window size)
 */
class MouseInteractionHandler(private val component: InteractiveComponent,
                              private val interactionBounds: Dimension) {
    private var prevDistance: Int = 0
    private val entrySideHandler: EntrySideHandler = EntrySideHandler()

    // The speed factor is used to adjust the speed of the scaling of the component
    private var speedFactor: Double = UIConstants.SPEED_FACTOR

    /**
     * Calculates the position where the component should be moved to based on the mouse position.
     *
     * @param x the x coordinate of the mouse position
     * @param y the y coordinate of the mouse position
     */
    private fun adjustComponentPosition(x: Int, y: Int) {
        component.position.x = x - component.currentSize.width / 2
        component.position.y = y - component.currentSize.height / 2
    }

    /**
     * Handles the mouse entering the window.
     * It detects the side from which the mouse enters and displays the component
     * at the minimum size (i.e. 1/4th of the original size of the component).
     *
     * @param x the x coordinate of the mouse position
     * @param y the y coordinate of the mouse position
     */
    fun handleMouseEntered(x: Int, y: Int) {
        entrySideHandler.detectEntrySide(x, y, interactionBounds.width, interactionBounds.height)

        val minSize = component.getMinSize()
        component.adjustCurrentSize(minSize.width, minSize.height)
        adjustComponentPosition(x, y)

        component.repaint()
    }

    /**
     * Handles the mouse moving within the window. If the entry side is empty,
     * that means the mouse has not entered the window yet, so the method returns.
     * Otherwise, it scales the component based on the distance the mouse moves from the entry side.
     *
     * @param x the x coordinate of the mouse position
     * @param y the y coordinate of the mouse position
     */
    fun handleMouseMoved(x: Int, y: Int){
        if (entrySideHandler.side.isEmpty()) return

        val minSize = component.getMinSize()
        val maxSize = component.getMaxSize()

        val currentDistance = entrySideHandler.getDistanceBasedOnEntrySide(x, y)
        val delta = currentDistance - prevDistance
        scaleComponent(delta, minSize, maxSize)
        adjustComponentPosition(x, y)
        component.repaint()

        prevDistance = currentDistance
    }

    /**
     * Scales the component based on the distance the mouse has moved,
     * and is clipped in the interval [1/4 * originalComponentSize, originalComponentSize],
     * to ensure the component does not become too small or too large.
     *
     * @param delta the distance the mouse has moved from its previous position
     * @param minSize the minimum size the component can be scaled to
     * @param maxSize the maximum size the component can be scaled to
     */
    private fun scaleComponent(delta: Int, minSize: Dimension, maxSize: Dimension) {
        val scalingFactor = (delta * speedFactor).toInt()

        val newWidth = (component.currentSize.width + scalingFactor).coerceIn(minSize.width, maxSize.width)
        val aspectRatio = component.currentSize.width.toDouble() / component.currentSize.height
        val newHeight = (newWidth / aspectRatio).toInt().coerceIn(minSize.height, maxSize.height)

        component.currentSize = Size(newWidth, newHeight)
    }

    /**
     * Handles the mouse exiting the window. It resets the component to its initial state.

     */
    fun reset() {
        component.position = Position()
        component.currentSize = Size()
        entrySideHandler.reset()
        prevDistance = 0
        component.repaint()
    }
}