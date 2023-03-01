package io.bry1337.pomfocus.android.extensions

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.Shadows
import io.bry1337.pomfocus.theme.setAlphaComponent

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Stable
fun Modifier.themeButton(sized: AppSizing = AppSizing.md) = height(
    sized.buttonScaling
)

@Stable
fun Modifier.themePaddingAll(sized: AppSizing = AppSizing.md) =
    padding(horizontal = sized.paddingScalingH, vertical = sized.paddingScalingV)

@Stable
fun Modifier.themePaddingTop(sized: AppSizing = AppSizing.md) = padding(top = sized.paddingScalingV)

@Stable
fun Modifier.themePaddingBottom(sized: AppSizing = AppSizing.md) =
    padding(bottom = sized.paddingScalingV)

@Stable
fun Modifier.themePaddingStart(sized: AppSizing = AppSizing.md) =
    padding(start = sized.paddingScalingH)

@Stable
fun Modifier.themePaddingEnd(sized: AppSizing = AppSizing.md) = padding(end = sized.paddingScalingH)

@Stable
fun Modifier.themePaddingV(sized: AppSizing = AppSizing.md) =
    padding(vertical = sized.paddingScalingV)

@Stable
fun Modifier.themePaddingH(sized: AppSizing = AppSizing.md) =
    padding(horizontal = sized.paddingScalingH)

@Stable
fun Modifier.themeShadow(
    sized: AppSizing = AppSizing.md,
    shape: Shape = RectangleShape,
    invert: Boolean = false
): Modifier {
    val shadows = when (sized) {
        AppSizing.xs -> Shadows.xs
        AppSizing.sm -> Shadows.sm
        AppSizing.md -> Shadows.md
        AppSizing.lg -> Shadows.lg
        else -> Shadows.lg
    }

    return shadows.fold(this) { modifier, shadow ->
        val offsetY = if (invert) {
            -shadow.offsetY
        } else {
            shadow.offsetY
        }
        return modifier.drawColoredShadow(
            color = shadow.color,
            radius = shadow.blurRadius.dp,
            x = shadow.offsetX.dp,
            y = offsetY.dp,
            shape = shape
        )
    }
}

// Refactored from: https://gist.github.com/cedrickring/0497965b0658d6727aaec531f59e8c5c
fun Modifier.drawColoredShadow(
    color: Long,
    radius: Dp = 20.dp,
    x: Dp = 0.dp,
    y: Dp = 0.dp,
    shape: Shape = RectangleShape
) = this.drawBehind {
    val transparentColor = setAlphaComponent(color, 0)
    drawIntoCanvas {
        val paint = Paint()
        // Get the internal backing paint object to get access to the shadow layer
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor.toInt()
        frameworkPaint.setShadowLayer(
            radius.toPx(),
            x.toPx(),
            y.toPx(),
            color.toInt()
        )

        val path = Path()
        path.addOutline(
            shape.createOutline(
                size = size,
                layoutDirection = layoutDirection,
                density = Density(density)
            )
        )

        it.drawPath(path = path, paint = paint)
    }
}
