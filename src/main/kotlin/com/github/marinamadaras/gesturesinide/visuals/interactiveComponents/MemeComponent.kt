package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * Implementation of an interactive component that displays a meme.
 */
class MemeComponent : InteractiveComponent() {

    private val memeImage: BufferedImage = ImageIO.read(this::class.java.getResource("/images/meme.png"))

    init {
        val minSize = getMinSize()
        adjustCurrentSize(minSize.width, minSize.height)
    }

    /**
     * Implementation of the render method that displays the meme image.
     */
    override fun render(g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        g.drawImage(memeImage, x, y, width, height, null)
    }

    /**
     * Returns the minimum size the component can reach in its interactions,
     * which is 1/4th of the meme's original size.
     */
    override fun getMinSize(): Dimension {
        return Dimension(memeImage.width / 4, memeImage.height / 4)
    }

    /**
     * Returns the maximum size the component can reach in its interactions,
     * which is the meme's original size.
     */
    override fun getMaxSize(): Dimension {
        return Dimension(memeImage.width, memeImage.height)
    }

}
