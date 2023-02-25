package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.tokens.ThemeTokens

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

/**
 * Theme interface representing
 */
data class Theme(
    val colors: ColorTable,
    val themePreset: ThemePreset,
    val typography: Typography = Typography,
    val dimen: Dimension = Dimension,
    val timing: Timing = Timing
) {
    val gradientBackground: Long
        get() = themePreset.gradientBackgroundColor(colors.isDarkScheme)
    val onBackgroundMuted: Long
        get() = setAlphaComponent(colors.onBackground, ThemeTokens.MutedContentOpacity)
    val onBackgroundSlightlyMuted: Long
        get() = setAlphaComponent(colors.onBackground, ThemeTokens.SlightlyMutedContentOpacity)

    val isDarkScheme: Boolean
        get() = colors.isDarkScheme
    val placeholder: Long
        get() = colors.placeholder
    val shimmer: Long
        get() = colors.shimmer
}
