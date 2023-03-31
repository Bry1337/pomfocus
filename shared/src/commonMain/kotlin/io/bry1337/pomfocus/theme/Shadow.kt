package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.tokens.AppColors

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

// Based from google box shadow effects
// https://medium.com/@Florian/freebie-google-material-design-shadow-helper-2a0501295a2d
// CSS Syntax: offset-x | offset-y | blur-radius | spread-radius | color
data class Shadow(
    val offsetX: Int = 0,
    val offsetY: Int = 0,
    val blurRadius: Int = 0,
    val spreadRadius: Int = 0,
    val color: Long = setAlphaComponent(AppColors.Black, 0.1)
)

object Shadows {
    val xs = listOf(
        Shadow(offsetY = 1, blurRadius = 3),
        Shadow(offsetY = 1, blurRadius = 2)
    )

    val sm = listOf(
        Shadow(offsetY = 3, blurRadius = 6),
        Shadow(offsetY = 3, blurRadius = 6)
    )

    val md = listOf(
        Shadow(offsetY = 10, blurRadius = 20),
        Shadow(offsetY = 6, blurRadius = 6)
    )

    val lg = listOf(
        Shadow(offsetY = 14, blurRadius = 24),
        Shadow(offsetY = 10, blurRadius = 10)
    )

    val xl = listOf(
        Shadow(offsetY = 19, blurRadius = 38),
        Shadow(offsetY = 15, blurRadius = 12)
    )
}
