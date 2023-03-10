package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.palettes.DefaultColorPalette
import io.bry1337.pomfocus.theme.palettes.MaterialColorPalette
import io.bry1337.pomfocus.theme.palettes.TonalColorPalette

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

enum class
ThemePreset(private val palette: TonalColorPalette, private val swatchName: String) {
    DEFAULT(DefaultColorPalette, DefaultColorPalette.SWATCH_NAME_DEFAULT),
    RED(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_RED),
    PINK(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_PINK),
    PURPLE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_PURPLE),
    DEEP_PURPLE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_DEEP_PURPLE),
    INDIGO(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_INDIGO),
    BLUE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_BLUE),
    LIGHT_BLUE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_LIGHT_BLUE),
    CYAN(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_CYAN),
    TEAL(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_TEAL),
    GREEN(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_GREEN),
    LIGHT_GREEN(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_LIGHT_GREEN),
    LIME(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_LIME),
    YELLOW(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_YELLOW),
    AMBER(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_AMBER),
    ORANGE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_ORANGE),
    DEEP_ORANGE(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_DEEP_ORANGE),
    BROWN(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_BROWN),
    GREY(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_GREY),
    BLUE_GREY(MaterialColorPalette, MaterialColorPalette.SWATCH_NAME_BLUE_GREY);


    fun primaryColor(isDarkScheme: Boolean): Long = palette.primaryColor(swatchName, isDarkScheme)
    fun primaryContentColor(isDarkScheme: Boolean): Long =
        palette.primaryContentColor(swatchName, isDarkScheme)

    fun gradientBackgroundColor(isDarkScheme: Boolean): Long =
        palette.gradientBackgroundColor(swatchName, isDarkScheme)

    fun primaryContainerColor(isDarkScheme: Boolean): Long = palette.primaryContainerColor(swatchName, isDarkScheme)

    fun surfaceColor(isDarkScheme: Boolean): Long = palette.surfaceColor(swatchName, isDarkScheme)

    companion object {
        val NAMES = values().map { it.name }
    }
}