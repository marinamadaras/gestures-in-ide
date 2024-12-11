package com.github.marinamadaras.gesturesinide

import com.github.marinamadaras.gesturesinide.visuals.ImagePanel
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object Utils {
    private fun getImage(resourcePath: String): BufferedImage {
        return ImageIO.read(
            this::class.java.getResource(resourcePath)
        )
    }

    fun createImagePanel(resourcePath: String): ImagePanel {
        return ImagePanel(getImage(resourcePath))
    }
}