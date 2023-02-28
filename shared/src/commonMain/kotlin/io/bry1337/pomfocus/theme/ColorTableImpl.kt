package io.bry1337.pomfocus.theme

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

// Reference
// https://m3.material.io/styles/color/the-color-system/color-roles#d6beb54f-54d8-4866-ad80-6f335c891f1b
internal data class ColorTableImpl(
    override val primary: Long,
    override val primaryContainer: Long,
    override val secondary: Long,
    override val secondaryContainer: Long,
    override val tertiary: Long,
    override val tertiaryContainer: Long,
    override val surface: Long,
    override val surfaceVariant: Long,
    override val background: Long,
    override val error: Long,
    override val errorContainer: Long,
    override val onPrimary: Long,
    override val onPrimaryContainer: Long,
    override val onSecondary: Long,
    override val onSecondaryContainer: Long,
    override val onTertiary: Long,
    override val onTertiaryContainer: Long,
    override val onSurface: Long,
    override val onSurfaceVariant: Long,
    override val onBackground: Long,
    override val onError: Long,
    override val onErrorContainer: Long,
    override val outline: Long,
    override val shadow: Long,
    override val surfaceTint: Long,
    override val inverseSurface: Long,
    override val inverseOnSurface: Long,
    override val inversePrimary: Long,
    override val scrim: Long,
    override val placeholder: Long,
    override val shimmer: Long,
    override val themePreset: ThemePreset,
    override val isDarkScheme: Boolean
) : ColorTable