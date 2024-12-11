package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import com.github.marinamadaras.gesturesinide.model.Position
import com.github.marinamadaras.gesturesinide.model.Size

import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

/**
 * An abstract class that represents an interactive component, i.e. the component that can be interacted with.
 * It acts as a wrapper for the component that needs to be displayed and updated, such that the component
 * can be a meme, a complex Swing element etc.
 *
 * @property position the position of the component on the screen
 * @property currentSize the current size of the component
 *           (Since the size can change dynamically based on user interaction and the wrapper's content
 *           may vary in type, tracking the size directly is more efficient than inferring it from
 *           specific implementations of this class)
 */
abstract class InteractiveComponent : JComponent() {
    var position = Position()
    var currentSize = Size()


    /**
     * Returns the minimum size the component can reach in its interactions.
     */
    abstract fun getMinSize(): Dimension

    /**
     * Returns the maximum size the component can reach in its interactions.
     */
    abstract fun getMaxSize(): Dimension

    /**
     * Has the logic for rendering the component on the screen.
     *
     * @param g The graphics object that will be used to render the component
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param width The width of the component
     * @param height The height of the component
     */
    abstract fun render(g: Graphics, x: Int, y: Int, width: Int, height: Int)

    /**
     * It displays the component with its contents on the screen.
     */
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        render(g, position.x, position.y, currentSize.width, currentSize.height)
    }

    /**
     * Setter method for the current size of the component.
     *
     * @param width The width of the component
     * @param height The height of the component
     */
    fun adjustCurrentSize(width: Int, height: Int) {
        currentSize.width = width
        currentSize.height = height
    }
}

