package com.github.marinamadaras.gesturesinide.handlers

import com.github.marinamadaras.gesturesinide.visuals.UIConstants
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.InteractiveComponent
import com.github.marinamadaras.gesturesinide.visuals.interactiveComponents.MemeComponent
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.assertj.core.api.Assertions.assertThat
import java.awt.Dimension
import java.awt.image.BufferedImage

class MouseInteractionHandlerTest() : BasePlatformTestCase() {
    private lateinit var component: InteractiveComponent
    private lateinit var handler: MouseInteractionHandler

    private val windowSize = Dimension(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT)
    private var testImage: BufferedImage =  BufferedImage(200, 280, BufferedImage.TYPE_INT_RGB)


    override fun setUp() {
        super.setUp()
        component = MemeComponent(testImage)
        handler = MouseInteractionHandler(component, windowSize)
    }

    fun `test entering from left side has right values`() {
        handler.handleMouseEntered(20, 250)

        // Ensure that the component has the minimum size (i.e. 1/4th of the original size of the component)
        assertThat(component.currentSize.width).isEqualTo(50)
        assertThat(component.currentSize.height).isEqualTo(70)

        assertThat(component.position.x).isEqualTo(-5)
        assertThat(component.position.y).isEqualTo(215)
    }

    fun `test entering from right side has right values`() {
        handler.handleMouseEntered(760, 499)

        // Ensure that the component has the minimum size (i.e. 1/4th of the original size of the component)
        assertThat(component.currentSize.width).isEqualTo(50)
        assertThat(component.currentSize.height).isEqualTo(70)

        assertThat(component.position.x).isEqualTo(735)
        assertThat(component.position.y).isEqualTo(464)
    }

    fun `test entering from top side has right values`() {
        handler.handleMouseEntered(144, 35)

        // Ensure that the component has the minimum size (i.e. 1/4th of the original size of the component)
        assertThat(component.currentSize.width).isEqualTo(50)
        assertThat(component.currentSize.height).isEqualTo(70)

        assertThat(component.position.x).isEqualTo(119)
        assertThat(component.position.y).isEqualTo(0)
    }

    fun `test entering from bottom side has right values`() {
        handler.handleMouseEntered(799, 580)

        // Ensure that the component has the minimum size (i.e. 1/4th of the original size of the component)
        assertThat(component.currentSize.width).isEqualTo(50)
        assertThat(component.currentSize.height).isEqualTo(70)

        assertThat(component.position.x).isEqualTo(774)
        assertThat(component.position.y).isEqualTo(545)
    }

    fun `test entering from right and moving to left scales the component up`(){
        handler.handleMouseEntered(760, 499)

        handler.handleMouseMoved(754, 503)

        assertThat(component.currentSize.width).isGreaterThan(50)
        assertThat(component.currentSize.height).isGreaterThan(70)

        assertThat(component.currentSize.width).isLessThan(200)
        assertThat(component.currentSize.height).isLessThan(280)

        assertThat(component.position.x).isNotEqualTo(-100)
        assertThat(component.position.y).isNotEqualTo(-100)
    }

    fun `test entering from right and moving cursor multiple times in left direction scales correctly`(){
        //mouse entered from right
        handler.handleMouseEntered(760, 499)

        //moved to the left
        handler.handleMouseMoved(754, 503)

        val prevWidth = component.currentSize.width
        val prevHeight = component.currentSize.height

        assertThat(component.currentSize.width).isGreaterThan(50)
        assertThat(component.currentSize.height).isGreaterThan(70)

        assertThat(component.currentSize.width).isLessThan(200)
        assertThat(component.currentSize.height).isLessThan(280)

        assertThat(component.position.x).isNotEqualTo(-100)
        assertThat(component.position.y).isNotEqualTo(-100)

        //moved to the left again
        handler.handleMouseMoved(722, 430)

        assertThat(component.currentSize.width).isGreaterThan(prevWidth)
        assertThat(component.currentSize.height).isGreaterThan(prevHeight)

        assertThat(component.currentSize.width).isLessThan(200)
        assertThat(component.currentSize.height).isLessThan(280)

        assertThat(component.position.x).isNotEqualTo(-100)
        assertThat(component.position.y).isNotEqualTo(-100)
    }

    fun `test entering from right and moving cursor in up direction does nothing`(){
        handler.handleMouseEntered(760, 499)

        handler.handleMouseMoved(760, 429)

        assertThat(component.currentSize.width).isEqualTo(50)
        assertThat(component.currentSize.height).isEqualTo(70)

        assertThat(component.position.x).isNotEqualTo(-100)
        assertThat(component.position.y).isNotEqualTo(-100)
    }

    fun `test resetting works`(){
        handler.handleMouseEntered(760, 499)

        handler.reset()

        assertThat(component.currentSize.width).isEqualTo(0)
        assertThat(component.currentSize.height).isEqualTo(0)

        assertThat(component.position.x).isEqualTo(-100)
        assertThat(component.position.y).isEqualTo(-100)
    }
}
