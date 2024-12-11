package com.github.marinamadaras.gesturesinide.visuals.interactiveComponents

import com.github.marinamadaras.gesturesinide.visuals.ImagePanel
import javax.imageio.ImageIO

class MemeComponent : InteractiveComponent() {

    init {
        val image = ImageIO.read(this::class.java.getResource("/images/meme.png"))
        val imagePanel = ImagePanel(image)
        content = imagePanel
    }
}
//class MemeComponent : InteractiveComponent(
//    ImagePanel(ImageIO.read(MemeComponent::class.java.getResource("/images/meme.png")))
//)