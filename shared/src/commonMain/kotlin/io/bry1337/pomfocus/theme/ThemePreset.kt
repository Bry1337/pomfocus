package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.palettes.DefaultColorPalette
import io.bry1337.pomfocus.theme.palettes.TonalColorPalette

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

enum class ThemePreset(private val palette: TonalColorPalette, private val swatchName: String) {
    DEFAULT(DefaultColorPalette, DefaultColorPalette.SWATCH_NAME_DEFAULT);

    fun primaryColor(isDarkScheme: Boolean): Long = palette.primaryColor(swatchName, isDarkScheme)
    fun primaryContentColor(isDarkScheme: Boolean): Long =
        palette.primaryContentColor(swatchName, isDarkScheme)

    fun gradientBackgroundColor(isDarkScheme: Boolean): Long =
        palette.gradientBackgroundColor(swatchName, isDarkScheme)

    companion object {
        val NAMES = values().map { it.name }
    }
}