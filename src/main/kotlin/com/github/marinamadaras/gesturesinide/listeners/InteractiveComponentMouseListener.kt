package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.handlers.MouseInteractionHandler
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter

class InteractiveComponentMouseListener(private val handler: MouseInteractionHandler) : MouseMotionAdapter() {
    /**
     * Listens for the cursor moving in order to perform the change
     * of size of the component,
     * and delegates the event to the handler.
     */
    override fun mouseMoved(e: MouseEvent) {
        handler.handleMouseMoved(e.x, e.y)
    }
}
