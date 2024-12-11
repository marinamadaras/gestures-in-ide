package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.handlers.MouseInteractionHandler
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent


class WindowAreaMouseListener(private val handler: MouseInteractionHandler) : MouseAdapter() {

    /**
     * Listens for the cursor entering the main window, and delegates the event to the handler.
     */
    override fun mouseEntered(e: MouseEvent) {
       handler.handleMouseEntered(e.x, e.y)
    }

    /**
     * Listens for the cursor exiting the main window, and resets the handler.
     */
    override fun mouseExited(e: MouseEvent) {
        handler.reset()
    }
}
