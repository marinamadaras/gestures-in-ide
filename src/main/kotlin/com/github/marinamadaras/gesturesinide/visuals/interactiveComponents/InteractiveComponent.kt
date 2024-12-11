package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import com.github.marinamadaras.gesturesinide.listeners.ContentMouseListener
import com.github.marinamadaras.gesturesinide.listeners.ContentMouseMotionListener
import com.github.marinamadaras.gesturesinide.visuals.EmptyPanel
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.JComponent
import kotlin.math.abs

//    var content: JComponent = EmptyPanel()
//        set(value) {
//            remove(field)
//            field = value
//            add(value)
//            value.setBounds(0, 0, width, height)
//            revalidate()
//            repaint()
//        }
//
//    private var contentX = -100
//    private var contentY = -100
//    private var contentSize = 50
//    private val minSize = 50 // 1/4 of full size
//    private val maxSize = 200 // Full size
//    private var prevMouseX = 0
//    private var prevMouseY = 0
//    private var growing = true // Is the size increasing?
//
//    init {
//        layout = null
//        add(content)
//
//        addMouseMotionListener(object : MouseMotionAdapter() {
//            override fun mouseMoved(e: MouseEvent) {
//                val deltaX = e.x - prevMouseX
//                val deltaY = e.y - prevMouseY
//
//                if (growing) {
//                    contentSize = (contentSize + deltaX.coerceAtLeast(deltaY)).coerceAtMost(maxSize)
//                } else {
//                    contentSize = (contentSize - deltaX.coerceAtLeast(deltaY)).coerceAtLeast(minSize)
//                }
//
//                // Update content position and size
//                contentX = e.x - contentSize / 2
//                contentY = e.y - contentSize / 2
//                content.setBounds(0, 0, width, height)
//                revalidate()
//                repaint()
//
//                prevMouseX = e.x
//                prevMouseY = e.y
//            }
//        })
//
//        addMouseListener(object : MouseAdapter() {
//            override fun mouseEntered(e: MouseEvent) {
//                // Reset content position
//                contentX = e.x - contentSize / 2
//                contentY = e.y - contentSize / 2
//                content.setBounds(0, 0, width, height)
//                revalidate()
//                repaint()
//            }
//
//            override fun mouseExited(e: MouseEvent) {
//                // Hide the content off-screen
//                contentX = -100
//                contentY = -100
//                content.setBounds(0, 0, width, height)
//                revalidate()
//                repaint()
//            }
//        })
//    }
//
//
//    override fun doLayout() {
//        super.doLayout()
//        content.setBounds(0, 0, width, height)
//    }
abstract class InteractiveComponent : JComponent() {
    var contentX: Int = -100
    var contentY: Int = -100
    var contentSize: Int = 0// Start at the initial size

    // Abstract methods to delegate size calculations to subclasses
    abstract fun getInitialSize(): Int // Initial size of the content
    abstract fun getMaxSize(): Int // Maximum allowed size for the content

    init {
        layout = null

        addMouseListener(ContentMouseListener(this))
        addMouseMotionListener(ContentMouseMotionListener(this))
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (contentX >= 0 && contentY >= 0) {
            render(g, contentX, contentY, contentSize, contentSize)
        }
    }

    // Abstract method for subclasses to implement their rendering logic
    abstract fun render(g: Graphics, x: Int, y: Int, width: Int, height: Int)

}

