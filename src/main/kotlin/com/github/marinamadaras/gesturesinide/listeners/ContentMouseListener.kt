package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class ContentMouseListener(private val component: InteractiveComponent) : MouseAdapter() {
    override fun mouseEntered(e: MouseEvent) {
        component.contentX = e.x - component.contentSize / 2
        component.contentY = e.y - component.contentSize / 2
        component.repaint()
    }

    override fun mouseExited(e: MouseEvent) {
        component.contentX = -100
        component.contentY = -100
        component.repaint()
    }
}