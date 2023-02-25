package io.bry1337.pomfocus.theme.palettes

import io.bry1337.pomfocus.theme.tokens.AppColors

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
            50 to 0xfffdf3e3,
            100 to 0xfffae0ba,
            200 to 0xfff8cc8e,
            300 to 0xfff6b863,
            400 to 0xfff4a948,
            500 to AppColors.DefaultPrimary,
            600 to 0xffec9136,
            700 to 0xffe58232,
            800 to 0xffde752f,
            900 to 0xffd1602b
        ).asNamedPair()
    )

    fun primaryColor(isDarkScheme: Boolean) =
        primaryColor(SWATCH_NAME_DEFAULT, isDarkScheme)
}