package com.github.marinamadaras.gesturesinide.managers

import com.github.marinamadaras.gesturesinide.config.PropertiesConfig
import com.github.marinamadaras.gesturesinide.listeners.WindowAreaMouseListener
import com.github.marinamadaras.gesturesinide.listeners.InteractiveComponentMouseListener
import com.github.marinamadaras.gesturesinide.visuals.MainWindow
import com.github.marinamadaras.gesturesinide.handlers.MouseInteractionHandler
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.ComplexComponent
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.MemeComponent
import java.awt.BorderLayout

/**
 * The WindowManager class is responsible for managing the window and the components that are displayed in it.
 *
 * It holds an instance of the window and displays the components in it.
 */
object WindowManager {
    private val window: MainWindow = MainWindow()

    private var showComplexComponent: Boolean = PropertiesConfig.getBoolean("show_complex_component")

    /**
     * Displays the window and initializes the component.
     */
    fun showWindow() {
        val component = createComponent()
        initializeComponent(component)
        if (component is ComplexComponent) {
            component.loadComponent()
        }
        window.isVisible = true
    }

    /**
     * Toggles the creation between the meme and complex components.
     */
    private fun createComponent(): InteractiveComponent {
        return if (showComplexComponent) ComplexComponent() else MemeComponent()
    }

    /**
     * Initializes the component in the window. It adds it to the window's content pane and sets up the listeners.
     *
     * @param component The component to be displayed and interacted with in relationship to the window
     */
    private fun initializeComponent(component: InteractiveComponent) {
        window.contentPane.removeAll()
        window.add(component, BorderLayout.CENTER)

        addListeners(component)

        window.revalidate()
        window.repaint()
    }

    /**
     * Adds the mouse listeners to the window and the component.
     *
     * @param component The interactive component on which that the listeners are concerned with
     */
    private fun addListeners(component: InteractiveComponent) {
        val handler = MouseInteractionHandler(component, window.getMainWindowSize())
        window.addMouseListener(WindowAreaMouseListener(handler))
        window.addMouseMotionListener(InteractiveComponentMouseListener(handler))
    }

}