package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter

class ContentMouseMotionListener(private val component: InteractiveComponent) : MouseMotionAdapter() {
    private var prevMouseX: Int = 0

    override fun mouseMoved(e: MouseEvent) {
        val deltaX = e.x - prevMouseX
        val minSize = component.getInitialSize()

        component.contentSize = if (deltaX > 0) {
            (component.contentSize + deltaX).coerceAtMost(component.getMaxSize())
        } else {
            (component.contentSize - kotlin.math.abs(deltaX)).coerceAtLeast(minSize)
        }

        component.contentX = e.x - component.contentSize / 2
        component.contentY = e.y - component.contentSize / 2
        component.repaint()

        prevMouseX = e.x
    }
}