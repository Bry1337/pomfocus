package io.bry1337.pomfocus.theme.palettes

import io.bry1337.pomfocus.theme.setAlphaComponent
import io.bry1337.pomfocus.theme.tokens.ThemeTokens

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

internal interface TonalColorPalette {
    val swatches: Map<String, ColorSwatch>

    operator fun get(name: String, tone: Int): Long? = swatches[name]?.tone(tone)
    fun primaryColor(swatchName: String, isDarkScheme: Boolean): Long =
        get(swatchName, primaryColorTone(isDarkScheme))!!
    fun primaryContentColor(swatchName: String, isDarkScheme: Boolean): Long =
        get(swatchName, primaryContentColorTone(isDarkScheme))!!
    fun gradientBackgroundColor(swatchName: String, isDarkScheme: Boolean): Long =
        setAlphaComponent(get(swatchName, gradientBackgroundColorTone(isDarkScheme))!!, ThemeTokens.gradientBackgroundOpacity)
    fun primaryColorTone(isDarkScheme: Boolean): Int = if (isDarkScheme) 400 else 500
    fun primaryContentColorTone(isDarkScheme: Boolean): Int = if (isDarkScheme) 100 else 900
    fun gradientBackgroundColorTone(isDarkScheme: Boolean): Int = if (isDarkScheme) 50 else 50
}
