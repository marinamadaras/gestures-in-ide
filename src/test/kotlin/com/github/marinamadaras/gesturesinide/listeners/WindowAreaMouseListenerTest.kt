package com.github.marinamadaras.gesturesinide.listeners

import com.github.marinamadaras.gesturesinide.handlers.MouseInteractionHandler
import com.github.marinamadaras.gesturesinide.visuals.MainWindow
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.awt.event.MouseEvent

class WindowAreaMouseListenerTest: BasePlatformTestCase(){
    private lateinit var window: MainWindow
    private lateinit var handler: MouseInteractionHandler
    private lateinit var listener: WindowAreaMouseListener

    override fun setUp() {
        super.setUp()

        window = MainWindow()
        handler = mock(MouseInteractionHandler::class.java)

        listener = WindowAreaMouseListener(handler)
        window.addMouseListener(listener)
    }

    fun `test mouse entered correctly delegates its logic`() {
        val x = 20
        val y = 250

        listener.mouseEntered(MouseEvent(window, MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, x, y, 0, false))

        verify(handler, times(1)).handleMouseEntered(x, y)
    }

    fun `test mouse exited correctly resets the handler`() {
        listener.mouseExited(MouseEvent(window, MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), 0, 0, 0, 0, false))

        verify(handler, times(1)).reset()
    }
}
