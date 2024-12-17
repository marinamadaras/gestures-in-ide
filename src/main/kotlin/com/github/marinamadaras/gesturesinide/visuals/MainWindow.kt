package com.github.marinamadaras.gesturesinide.visuals

import com.github.marinamadaras.gesturesinide.UIConstants
import java.awt.Dimension
import javax.swing.JFrame

/**
 * The main window of the application, on which the interactive components are displayed
 */
class MainWindow : JFrame() {
    init {
        title = "Gestures in IDE Window"
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT)
        setLocationRelativeTo(null)
    }

    /**
     * Method that returns the size of the main window
     */
    fun getMainWindowSize(): Dimension = size
}