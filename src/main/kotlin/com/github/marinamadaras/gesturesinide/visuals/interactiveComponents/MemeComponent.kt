package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class MemeComponent : InteractiveComponent() {

private val memeImage: BufferedImage = ImageIO.read(this::class.java.getResource("/images/meme.png"))


    override fun render(g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        g.drawImage(memeImage, x, y, width, height, null)
    }

    override fun getInitialSize(): Int {
        // Set initial size based on intrinsic size of the image
        return memeImage.width.coerceAtMost(memeImage.height) / 4
    }

    override fun getMaxSize(): Int {
        // Maximum size is a proportion of the panel size or intrinsic size
        return (width.coerceAtMost(height)).coerceAtMost(memeImage.width)
    }
}
