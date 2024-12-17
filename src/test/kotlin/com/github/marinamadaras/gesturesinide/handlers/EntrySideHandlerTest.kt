package com.github.marinamadaras.gesturesinide.handlers

import com.github.marinamadaras.gesturesinide.model.Position
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.assertj.core.api.Assertions.assertThat

class EntrySideHandlerTest : BasePlatformTestCase() {
    private lateinit var entrySideHandler: EntrySideHandler

    fun `test distance from left side has correct values`() {
        entrySideHandler = EntrySideHandler("LEFT", Position(10, 250))

        val distance = entrySideHandler.getDistanceBasedOnEntrySide(20, 250)

        assertThat(distance).isEqualTo(10)
    }

    fun `test distance from right side has correct values`() {
        entrySideHandler = EntrySideHandler("RIGHT", Position(300, 250))

        val distance = entrySideHandler.getDistanceBasedOnEntrySide(290, 250)

        assertThat(distance).isEqualTo(10)
    }

    fun `test distance from top side has correct values`() {
        entrySideHandler = EntrySideHandler("TOP", Position(250, 50))

        val distance = entrySideHandler.getDistanceBasedOnEntrySide(250, 60)

        assertThat(distance).isEqualTo(10)
    }

    fun `test distance from bottom side has correct values`() {
        entrySideHandler = EntrySideHandler("BOTTOM", Position(250, 300))

        val distance = entrySideHandler.getDistanceBasedOnEntrySide(250, 290)

        assertThat(distance).isEqualTo(10)
    }

    fun `test default side returns zero distance`() {
        entrySideHandler = EntrySideHandler("UNKNOWN", Position(250, 300))

        val distance = entrySideHandler.getDistanceBasedOnEntrySide(250, 290)

        assertThat(distance).isEqualTo(0)
    }

    fun `test reset clears side and entry position`() {
        entrySideHandler = EntrySideHandler("LEFT", Position(10, 250))

        entrySideHandler.reset()

        assertThat(entrySideHandler.side).isEmpty()
    }

}