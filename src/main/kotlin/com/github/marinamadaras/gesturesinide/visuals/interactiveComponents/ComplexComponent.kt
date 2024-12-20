package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import com.github.marinamadaras.gesturesinide.model.Position
import com.github.marinamadaras.gesturesinide.visuals.GraphicsUtils
import com.intellij.ui.JBColor
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.SwingWorker
import javax.swing.Timer

class ComplexComponent: InteractiveComponent(){
    private var isLoaded = false
    private var progress = 0

    /**
     * Returns the minimum size the component can reach in its interactions,
     * which is hardcoded for this.
     */
    override fun getMinSize(): Dimension {
       return Dimension(80, 80)
    }

    /**
     * Returns the maximum size the component can reach in its interactions,
     * which is the meme's original size.
     */
    override fun getMaxSize(): Dimension {
        return Dimension(320, 320)
    }

    /**
     * Implementation of the render method that displays the meme image.
     *
     * @param g the graphics object to draw on
     * @param x the x coordinate of the component
     * @param y the y coordinate of the component
     * @param width the width of the component
     * @param height the height of the component
     */
    override fun render(g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        if (!isLoaded) {
            displayDummyComponent(g, x, y, width, height)
        } else {
           displayActualComponent(g, x, y, width, height)
        }
    }

    /**
     * Method that simulates a complex UI panel loading,
     * with a background thread mimicking a long loading process.
     *
     * It uses a SwingWorker to simulate the loading process and
     * a Timer to create a progress bar.
     *
     * It relies on 2 parameters:
     *  - progress: the progress of the loading process, between [0, 100]
     *  - isLoaded: a boolean that indicates if the component has finished loading
     */
    fun loadComponent() {
        val timer = Timer(200) { e ->
            if (progress < 100) {
                progress += 5
                repaint()
            } else {
                (e.source as Timer).stop()
            }
        }
        timer.start()

        val worker = object : SwingWorker<Unit, Unit>() {
            override fun doInBackground() {
                Thread.sleep(4000)
            }
            override fun done() {
                isLoaded = true
                repaint()
            }
        }
        worker.execute()
    }

    /**
     * Helper method for the rendering of the component, that displays
     * a dummy component with a loading message and a progress bar.
     *
     * @param g the graphics object to draw on
     * @param x the x coordinate of the component
     * @param y the y coordinate of the component
     * @param width the width of the component
     * @param height the height of the component
     */
    private fun displayDummyComponent(g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        g.color = JBColor.LIGHT_GRAY
        g.fillRect(x, y, width, height)

        GraphicsUtils.drawText(g, "Loading...",
            Dimension(width, height),
            Position(x, y),
            JBColor.BLACK,
            1.2)
        GraphicsUtils.drawProgressBar(g, Dimension(width, height), Position(x, y), progress)
    }

    /**
     * Helper method for the rendering of the component, that displays
     * the actual "complex" component. Currently, it's just a rectangle with text
     * on it, but it can be extended to display more complex UI elements.
     *
     * @param g the graphics object to draw on
     * @param x the x coordinate of the component
     * @param y the y coordinate of the component
     * @param width the width of the component
     * @param height the height of the component
     */
    private fun displayActualComponent(g: Graphics, x: Int, y: Int, width: Int, height: Int){
        g.color = JBColor.BLUE
        g.fillRect(x, y, width, height)

        GraphicsUtils.drawText(g, "This is actually",
            Dimension(width, height),
            Position(x, y),
            JBColor.WHITE,
            1.2)
        GraphicsUtils.drawText(g, "a very complex element",
            Dimension(width, height),
            Position(x, y),
            JBColor.WHITE)
    }
}