package com.github.marinamadaras.gesturesinide.visuals

import com.github.marinamadaras.gesturesinide.model.Position
import com.intellij.ui.JBColor
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics

object GraphicsUtils {

    /**
     * Draws a progress bar within another component, knowing where the
     * current component position (its origin) is and the dimensions of the component.
     *
     * @param g the graphics object to draw on
     * @param dimension the dimension of the **parent** component
     * @param position the current position of the **parent** component(its origin)
     * @param progress the progress of the loading process, between [0, 100]
     */
    fun drawProgressBar(g: Graphics, dimension: Dimension, position: Position, progress: Int) {
        val progressBarWidth = (dimension.width * 0.6).toInt()
        val progressBarHeight = (dimension.height * 0.05).toInt()

        val progressBarX = position.x + (dimension.width - progressBarWidth) / 2
        val progressBarY = position.y + (dimension.height - progressBarHeight) / 2

        g.color = JBColor.DARK_GRAY
        g.fillRect(progressBarX, progressBarY, progressBarWidth, progressBarHeight)

        g.color = JBColor.BLUE
        val filledWidth = (progressBarWidth * progress / 100)
        g.fillRect(progressBarX, progressBarY, filledWidth, progressBarHeight)

        g.color = JBColor.BLACK
        g.drawRect(progressBarX, progressBarY, progressBarWidth, progressBarHeight)
    }

    /**
     * Draws a text within another component, knowing where the
     * current component position (its origin) is and the dimensions of the component.
     * The text is centered horizontally within the component.
     * The baseline adjustment is used to adjust the vertical position of the text.
     * By default, it is centered vertically, but it can be adjusted to be higher or lower.
     *
     * @param g the graphics object to draw on
     * @param text the text to be displayed
     * @param dimension the dimension of the **parent** component
     * @param position the current position of the **parent** component(its origin)
     * @param color the color of the text
     * @param baselineAdjustment the adjustment of the baseline of the text
     */
    fun drawText(
        g: Graphics,
        text: String,
        dimension: Dimension,
        position: Position,
        color: JBColor,
        baselineAdjustment: Double = 0.0
    ) {
        g.color = color
        g.font = Font("Bauhaus", Font.BOLD, 10)

        val fontMetrics = g.fontMetrics
        val textWidth = fontMetrics.stringWidth(text)
        val textHeight = fontMetrics.height

        val textX = position.x + (dimension.width - textWidth) / 2
        val adjustedTextHeight = textHeight * baselineAdjustment
        val textY = position.y + (dimension.height + textHeight) / 2 - adjustedTextHeight.toInt()

        g.drawString(text, textX, textY)
    }
}