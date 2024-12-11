package com.github.marinamadaras.gesturesinide.visuals.managers

import com.github.marinamadaras.gesturesinide.visuals.MainWindow
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.MemeComponent
import java.awt.BorderLayout

object WindowManager {
    private val window: MainWindow = MainWindow()

    private fun displayComponent(component: InteractiveComponent) {
        window.contentPane.removeAll()
        window.add(component, BorderLayout.CENTER)
        window.revalidate()
        window.repaint()
    }

    fun showWindow() {
        displayComponent(MemeComponent())
        window.isVisible = true
    }
}