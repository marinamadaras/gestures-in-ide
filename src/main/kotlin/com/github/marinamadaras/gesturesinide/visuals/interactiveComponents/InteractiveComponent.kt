package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import javax.swing.JComponent

abstract class InteractiveComponent : JComponent() {
    var content: JComponent? = null
        set(value) {
            if (field != null) {
                remove(field)
            }
            field = value
            if (value != null) {
                add(value) // Add the new content to the hierarchy
                value.setBounds(0, 0, width, height) // Match size to parent
            }
            revalidate() // Ensure layout updates
            repaint() // Redraw to display new content
        }

    override fun doLayout() {
        super.doLayout()
        content?.setBounds(0, 0, width, height)
    }

}
//abstract class InteractiveComponent(private val content: JComponent) : JComponent() {
//    init {
//        layout = null
//        add(content)
//    }
//
//    override fun doLayout() {
//        super.doLayout()
//        content.setBounds(0, 0, width, height)
//    }
//}
