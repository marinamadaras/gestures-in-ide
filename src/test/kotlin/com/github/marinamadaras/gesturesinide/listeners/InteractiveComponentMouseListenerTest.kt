package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.handlers.MouseInteractionHandler
import com.github.marinamadaras.gesturesinide.visuals.MainWindow
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.awt.event.MouseEvent

class InteractiveComponentMouseListenerTest : BasePlatformTestCase(){
    private lateinit var window: MainWindow
    private lateinit var handler: MouseInteractionHandler
    private lateinit var listener: InteractiveComponentMouseListener

    override fun setUp() {
        super.setUp()

        window = MainWindow()
        handler = mock(MouseInteractionHandler::class.java)

        listener = InteractiveComponentMouseListener(handler)
        window.addMouseMotionListener(listener)
    }

    fun `test mouse moved correctly delegates its logic`() {
        val x = 20
        val y = 250

        listener.mouseMoved(MouseEvent(window, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false))

        verify(handler, times(1)).handleMouseMoved(x, y)
    }
}
