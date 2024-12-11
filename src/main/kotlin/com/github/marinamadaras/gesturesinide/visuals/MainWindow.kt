package com.github.marinamadaras.gesturesinide.visuals

import javax.swing.JFrame

class MainWindow : JFrame() {
    init {
        title = "Gestures in IDE Window"
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(800, 600)
        setLocationRelativeTo(null)
    }
}