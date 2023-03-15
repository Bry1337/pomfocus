package io.bry1337.pomfocus.theme

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

data class TextStyle(
    val fontWeight: FontWeight = FontWeight.Normal,
    val fontSize: Double,
    val lineHeight: Double? = null,
    val letterSpacing: Double = 0.0
)

enum class FontWeight(val weight: Int) {
    Thin(100),
    ExtraLight(200),
    Light(300),
    Normal(400),
    Medium(500),
    SemiBold(600),
    Bold(700),
    ExtraBold(800),
    Black(900)
}
