package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.tokens.AppColors

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

/**
 * Represents common colors used in the application theme.  Based on system color tokens defined in
 * Material Design 3 Baseline Color Schemes
 *
 * @see [Material design 3 color table](https://m3.material.io/styles/color/the-color-system/tokens#7fd4440e-986d-443f-8b3a-4933bff16646)
 * @see [Material design 3 color roles](https://m3.material.io/styles/color/the-color-system/color-roles)
 * @see [Material design 2 color roles](https://material.io/design/color/the-color-system.html#color-theme-creation)
 */
sealed interface ColorTable {
    val primary: Long
    val primaryContainer: Long
    val secondary: Long
    val secondaryContainer: Long
    val tertiary: Long
    val tertiaryContainer: Long
    val surface: Long
    val surfaceVariant: Long
    val background: Long
    val error: Long
    val errorContainer: Long
    val onPrimary: Long
    val onPrimaryContainer: Long
    val onSecondary: Long
    val onSecondaryContainer: Long
    val onTertiary: Long
    val onTertiaryContainer: Long
    val onSurface: Long
    val onSurfaceVariant: Long
    val onBackground: Long
    val onError: Long
    val onErrorContainer: Long
    val outline: Long
    val shadow: Long
    val surfaceTint: Long
    val inverseSurface: Long
    val inverseOnSurface: Long
    val inversePrimary: Long
    val scrim: Long
    val placeholder: Long
    val shimmer: Long
    val themePreset: ThemePreset
    val isDarkScheme: Boolean

    companion object {
        /**
         * Creates a light color scheme table with defaults values listed in Material Design 3 docs:
         * https://m3.material.io/styles/color/the-color-system/tokens#7fd4440e-986d-443f-8b3a-4933bff16646
         */
        private fun lightScheme(
            primary: Long = 0xFF6750A4,
            primaryContainer: Long = 0xFFEADDFF,
            secondary: Long = 0xFF625B71,
            secondaryContainer: Long = 0xFFE8DEF8,
            tertiary: Long = 0xFF7D5260,
            tertiaryContainer: Long = 0xFFFFD8E4,
            surface: Long = 0xFFFFFBFE,
            surfaceVariant: Long = 0xFFE7E0EC,
            background: Long = 0xFFFFFBFE,
            error: Long = 0xFFB3261E,
            errorContainer: Long = 0xFFF9DEDC,
            onPrimary: Long = 0xFFFFFFFF,
            onPrimaryContainer: Long = 0xFF21005E,
            onSecondary: Long = 0xFFFFFFFF,
            onSecondaryContainer: Long = 0xFF1E192B,
            onTertiary: Long = 0xFFFFFFFF,
            onTertiaryContainer: Long = 0xFF370B1E,
            onSurface: Long = 0xFF1C1B1F,
            onSurfaceVariant: Long = 0xFF49454E,
            onBackground: Long = 0xFF1C1B1F,
            onError: Long = 0xFFFFFFFF,
            onErrorContainer: Long = 0xFF370B1E,
            outline: Long = 0xFF79747E,
            shadow: Long = 0xFF000000,
            surfaceTint: Long = 0xFF6750A4,
            inverseSurface: Long = 0xFF313033,
            inverseOnSurface: Long = 0xFFF4EFF4,
            inversePrimary: Long = 0xFFD0BCFF,
            scrim: Long = 0xFF000000,
            placeholder: Long = 0xFFF2F7FF,
            shimmer: Long = 0xFFFFFFFF,
            themePreset: ThemePreset
        ): ColorTable = ColorTableImpl(
            primary = primary,
            primaryContainer = primaryContainer,
            secondary = secondary,
            secondaryContainer = secondaryContainer,
            tertiary = tertiary,
            tertiaryContainer = tertiaryContainer,
            surface = surface,
            surfaceVariant = surfaceVariant,
            background = background,
            error = error,
            errorContainer = errorContainer,
            onPrimary = onPrimary,
            onPrimaryContainer = onPrimaryContainer,
            onSecondary = onSecondary,
            onSecondaryContainer = onSecondaryContainer,
            onTertiary = onTertiary,
            onTertiaryContainer = onTertiaryContainer,
            onSurface = onSurface,
            onSurfaceVariant = onSurfaceVariant,
            onBackground = onBackground,
            onError = onError,
            onErrorContainer = onErrorContainer,
            outline = outline,
            shadow = shadow,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            inversePrimary = inversePrimary,
            scrim = scrim,
            placeholder = placeholder,
            shimmer = shimmer,
            themePreset = themePreset,
            isDarkScheme = false
        )

        /**
         * Creates a dark color scheme table with defaults values listed in Material Design 3 docs:
         * https://m3.material.io/styles/color/the-color-system/tokens#7fd4440e-986d-443f-8b3a-4933bff16646
         */
        private fun darkScheme(
            primary: Long = 0xFFD0BCFF,
            primaryContainer: Long = 0xFF4F378B,
            secondary: Long = 0xFFCCC2DC,
            secondaryContainer: Long = 0xFF4A4458,
            tertiary: Long = 0xFFEFB8C8,
            tertiaryContainer: Long = 0xFF633B48,
            surface: Long = 0xFF1C1B1F,
            surfaceVariant: Long = 0xFF49454F,
            background: Long = 0xFF1C1B1F,
            error: Long = 0xFFF2B8B5,
            errorContainer: Long = 0xFF8C1D18,
            onPrimary: Long = 0xFF371E73,
            onPrimaryContainer: Long = 0xFFEADDFF,
            onSecondary: Long = 0xFF332D41,
            onSecondaryContainer: Long = 0xFFE8DEF8,
            onTertiary: Long = 0xFF492532,
            onTertiaryContainer: Long = 0xFFFFD8E4,
            onSurface: Long = 0xFFE6E1E5,
            onSurfaceVariant: Long = 0xFFCAC4D0,
            onBackground: Long = 0xFFE6E1E5,
            onError: Long = 0xFF601410,
            onErrorContainer: Long = 0xFFF9DEDC,
            outline: Long = 0xFF938F99,
            shadow: Long = 0xFF000000,
            surfaceTint: Long = 0xFFD0BCFF,
            inverseSurface: Long = 0xFFE6E1E5,
            inverseOnSurface: Long = 0xFF313033,
            inversePrimary: Long = 0xFF6750A4,
            scrim: Long = 0xFF000000,
            placeholder: Long = 0xFF1C1C1E,
            shimmer: Long = 0xFFFFFFFF,
            themePreset: ThemePreset
        ): ColorTable = ColorTableImpl(
            primary = primary,
            primaryContainer = primaryContainer,
            secondary = secondary,
            secondaryContainer = secondaryContainer,
            tertiary = tertiary,
            tertiaryContainer = tertiaryContainer,
            surface = surface,
            surfaceVariant = surfaceVariant,
            background = background,
            error = error,
            errorContainer = errorContainer,
            onPrimary = onPrimary,
            onPrimaryContainer = onPrimaryContainer,
            onSecondary = onSecondary,
            onSecondaryContainer = onSecondaryContainer,
            onTertiary = onTertiary,
            onTertiaryContainer = onTertiaryContainer,
            onSurface = onSurface,
            onSurfaceVariant = onSurfaceVariant,
            onBackground = onBackground,
            onError = onError,
            onErrorContainer = onErrorContainer,
            outline = outline,
            shadow = shadow,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            inversePrimary = inversePrimary,
            scrim = scrim,
            placeholder = placeholder,
            shimmer = shimmer,
            themePreset = themePreset,
            isDarkScheme = true
        )

        fun appColors(
            themePreset: ThemePreset,
            isDarkScheme: Boolean = false
        ): ColorTable {
            val primary = themePreset.primaryColor(isDarkScheme)
            val primaryContent = themePreset.primaryContentColor(isDarkScheme)
            val darkTextColor = AppColors.DarkText
            val lightTextColor = AppColors.LightText
            val primaryContainer = setAlphaComponent(primary, 0.2)
            val onPrimary = if (isDarkScheme) darkTextColor else lightTextColor
            val (background, onBackground) = derivedBackgroundAndText(
                isDarkScheme,
                AppColors.LightBackground,
                AppColors.DarkBackground
            )
            val (surface, onSurface) = derivedBackgroundAndText(
                isDarkScheme,
                AppColors.LightSurface,
                AppColors.DarkSurface
            )
            val (surfaceVariant, onSurfaceVariant) = derivedBackgroundAndText(
                isDarkScheme,
                AppColors.LightSurfaceVariant,
                AppColors.DarkSurfaceVariant,
                alpha = 0.1
            )
            val outline = setAlphaComponent(onBackground, 0.2)

            return if (isDarkScheme) {
                darkScheme(
                    primary = primary,
                    primaryContainer = primaryContainer,
                    secondary = primary,
                    secondaryContainer = primaryContainer,
                    tertiary = primary,
                    tertiaryContainer = primaryContainer,
                    surface = surface,
                    surfaceVariant = surfaceVariant,
                    background = background,
                    onPrimary = onPrimary,
                    onPrimaryContainer = primaryContent,
                    onSecondary = onPrimary,
                    onSecondaryContainer = primaryContent,
                    onTertiary = onPrimary,
                    onTertiaryContainer = primaryContent,
                    onSurface = onSurface,
                    onSurfaceVariant = onSurfaceVariant,
                    onBackground = onBackground,
                    outline = outline,
                    themePreset = themePreset
                )
            } else {
                lightScheme(
                    primary = primary,
                    primaryContainer = primaryContainer,
                    secondary = primary,
                    secondaryContainer = primaryContainer,
                    tertiary = primary,
                    tertiaryContainer = primaryContainer,
                    surface = surface,
                    surfaceVariant = surfaceVariant,
                    background = background,
                    onPrimaryContainer = primaryContent,
                    onSecondary = onPrimary,
                    onSecondaryContainer = primaryContent,
                    onTertiary = onPrimary,
                    onTertiaryContainer = primaryContent,
                    onSurface = onSurface,
                    onSurfaceVariant = onSurfaceVariant,
                    onBackground = onBackground,
                    outline = outline,
                    themePreset = themePreset
                )
            }
        }

        private fun derivedBackgroundAndText(
            isDarkScheme: Boolean,
            lightBackground: Long,
            darkBackground: Long,
            alpha: Double = 1.0
        ): Pair<Long, Long> {
            val background =
                if (isDarkScheme) darkBackground else lightBackground
            val content =
                if (shouldUseDarkOn(background)) AppColors.DarkText else AppColors.LightText
            val contentWithAlpha = if (alpha < 1.0) setAlphaComponent(content, alpha) else content
            return Pair(background, contentWithAlpha)
        }

        private fun shouldUseDarkOn(backgroundColor: Long): Boolean {
            // @see https://android.googlesource.com/platform/frameworks/base.git/+/master/core/java/com/android/internal/util/ContrastColorUtil.java#595
            // @see https://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
            // Color contrast ratio luminance midpoint, X: 1.05 / (X + 0.05) = (X + 0.05) / 0.05
            // Solved as X = sqrt(.05 * 1.05) - 0.05 = 0.17912878474
            return calculateLuminance(backgroundColor) > 0.17912878474
        }
    }
}