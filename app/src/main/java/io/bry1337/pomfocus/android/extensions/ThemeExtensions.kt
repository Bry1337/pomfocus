package io.bry1337.pomfocus.android.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.theme.Theme

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

fun Theme.materialColorScheme(): ColorScheme =
    if (colors.isDarkScheme) {
        darkColorScheme(
            primary = colors.primary.argbToColor(),
            onPrimary = colors.onPrimary.argbToColor(),
            primaryContainer = colors.primaryContainer.argbToColor(),
            onPrimaryContainer = colors.onPrimaryContainer.argbToColor(),
            inversePrimary = colors.inversePrimary.argbToColor(),
            secondary = colors.secondary.argbToColor(),
            onSecondary = colors.onSecondary.argbToColor(),
            secondaryContainer = colors.secondaryContainer.argbToColor(),
            onSecondaryContainer = colors.onSecondaryContainer.argbToColor(),
            tertiary = colors.tertiary.argbToColor(),
            onTertiary = colors.onTertiary.argbToColor(),
            tertiaryContainer = colors.tertiaryContainer.argbToColor(),
            onTertiaryContainer = colors.onTertiaryContainer.argbToColor(),
            background = colors.background.argbToColor(),
            onBackground = colors.onBackground.argbToColor(),
            surface = colors.surface.argbToColor(),
            onSurface = colors.onSurface.argbToColor(),
            surfaceVariant = colors.surfaceVariant.argbToColor(),
            onSurfaceVariant = colors.onSurfaceVariant.argbToColor(),
            surfaceTint = colors.surfaceTint.argbToColor(),
            inverseSurface = colors.inverseSurface.argbToColor(),
            inverseOnSurface = colors.inverseOnSurface.argbToColor(),
            error = colors.error.argbToColor(),
            onError = colors.onError.argbToColor(),
            errorContainer = colors.errorContainer.argbToColor(),
            onErrorContainer = colors.onErrorContainer.argbToColor(),
            outline = colors.outline.argbToColor()
        )
    } else {
        lightColorScheme(
            primary = colors.primary.argbToColor(),
            onPrimary = colors.onPrimary.argbToColor(),
            primaryContainer = colors.primaryContainer.argbToColor(),
            onPrimaryContainer = colors.onPrimaryContainer.argbToColor(),
            inversePrimary = colors.inversePrimary.argbToColor(),
            secondary = colors.secondary.argbToColor(),
            onSecondary = colors.onSecondary.argbToColor(),
            secondaryContainer = colors.secondaryContainer.argbToColor(),
            onSecondaryContainer = colors.onSecondaryContainer.argbToColor(),
            tertiary = colors.tertiary.argbToColor(),
            onTertiary = colors.onTertiary.argbToColor(),
            tertiaryContainer = colors.tertiaryContainer.argbToColor(),
            onTertiaryContainer = colors.onTertiaryContainer.argbToColor(),
            background = colors.background.argbToColor(),
            onBackground = colors.onBackground.argbToColor(),
            surface = colors.surface.argbToColor(),
            onSurface = colors.onSurface.argbToColor(),
            surfaceVariant = colors.surfaceVariant.argbToColor(),
            onSurfaceVariant = colors.onSurfaceVariant.argbToColor(),
            surfaceTint = colors.surfaceTint.argbToColor(),
            inverseSurface = colors.inverseSurface.argbToColor(),
            inverseOnSurface = colors.inverseOnSurface.argbToColor(),
            error = colors.error.argbToColor(),
            onError = colors.onError.argbToColor(),
            errorContainer = colors.errorContainer.argbToColor(),
            onErrorContainer = colors.onErrorContainer.argbToColor(),
            outline = colors.outline.argbToColor()
        )
    }

fun Theme.materialTypography(): Typography = Typography(
    displayLarge = this.typography.displayLarge.toMaterialStyle(),
    displayMedium = this.typography.displayMedium.toMaterialStyle(),
    displaySmall = this.typography.displaySmall.toMaterialStyle(),
    headlineLarge = this.typography.headlineLarge.toMaterialStyle(),
    headlineMedium = this.typography.headlineMedium.toMaterialStyle(),
    headlineSmall = this.typography.headlineSmall.toMaterialStyle(),
    titleLarge = this.typography.titleLarge.toMaterialStyle(),
    titleMedium = this.typography.titleMedium.toMaterialStyle(),
    titleSmall = this.typography.titleSmall.toMaterialStyle(),
    bodyLarge = this.typography.bodyLarge.toMaterialStyle(),
    bodyMedium = this.typography.bodyMedium.toMaterialStyle(),
    bodySmall = this.typography.bodySmall.toMaterialStyle(),
    labelLarge = this.typography.labelLarge.toMaterialStyle(),
    labelMedium = this.typography.labelMedium.toMaterialStyle(),
    labelSmall = this.typography.labelSmall.toMaterialStyle()
)

fun Theme.appShapes() = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
