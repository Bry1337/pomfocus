package io.bry1337.pomfocus.theme.palettes

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

// Swatch generated utilizing:
// - https://material.io/design/color/the-color-system.html#tools-for-picking-colors
// - https://material.io/resources/color/#!/?view.left=0&view.right=0&primary.color=f19c3a
internal object DefaultColorPalette : TonalColorPalette {
    const val SWATCH_NAME_DEFAULT = "default"

    override val swatches = mapOf(
        ColorSwatch(
            SWATCH_NAME_DEFAULT,
            50 to 0xffe6f6ea,
            100 to 0xffc3e9cb,
            200 to 0xff9cdba9,
            300 to 0xff72ce86,
            400 to 0xff4ec36c,
            500 to 0xff22b851,
            600 to 0xff16a848,
            700 to 0xff00963c,
            800 to 0xff008531,
            900 to 0xff00661c
        ).asNamedPair()
    )

    fun primaryColor(isDarkScheme: Boolean) =
        primaryColor(SWATCH_NAME_DEFAULT, isDarkScheme)
}