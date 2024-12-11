package com.github.marinamadaras.gesturesinide.visuals

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JComponent


class ImagePanel(private val image: BufferedImage) : JComponent() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(image, 0, 0, width, height, this)
        println("Content set")
    }
}