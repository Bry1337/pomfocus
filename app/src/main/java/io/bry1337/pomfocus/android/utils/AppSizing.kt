package io.bry1337.pomfocus.android.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface AppSizing {

    object xs : AppSizing
    object sm : AppSizing
    object md : AppSizing
    object lg : AppSizing
    object xl : AppSizing
    object xxl : AppSizing

    val buttonScaling: Dp
        get() = when (this) {
            xs -> DimensionConstants.buttonSizing.xs.dp
            sm -> DimensionConstants.buttonSizing.sm.dp
            md -> DimensionConstants.buttonSizing.md.dp
            lg -> DimensionConstants.buttonSizing.lg.dp
            xl -> DimensionConstants.buttonSizing.xl.dp
            else -> DimensionConstants.buttonSizing.xxl.dp
        }

    val roundedCornersScaling: Dp
        get() = when (this) {
            xs -> DimensionConstants.roundedCornersXs.dp
            sm -> DimensionConstants.roundedCornersSm.dp
            md -> DimensionConstants.roundedCornersMd.dp
            lg -> DimensionConstants.roundedCornersLg.dp
            xl -> DimensionConstants.roundedCornersXl.dp
            xxl -> DimensionConstants.roundedCornersFull.dp
            else -> DimensionConstants.roundedCornersNone.dp
        }

    // Horizontal padding scaling
    val paddingScalingH: Dp
        get() = when (this) {
            xs -> DimensionConstants.hSpacing.xs.dp
            sm -> DimensionConstants.hSpacing.sm.dp
            md -> DimensionConstants.hSpacing.md.dp
            lg -> DimensionConstants.hSpacing.lg.dp
            xl -> DimensionConstants.hSpacing.xl.dp
            else -> DimensionConstants.hSpacing.xxl.dp
        }

    // Vertical padding scaling
    val paddingScalingV: Dp
        get() = when (this) {
            xs -> DimensionConstants.vSpacing.xs.dp
            sm -> DimensionConstants.vSpacing.sm.dp
            md -> DimensionConstants.vSpacing.md.dp
            lg -> DimensionConstants.vSpacing.lg.dp
            xl -> DimensionConstants.vSpacing.xl.dp
            else -> DimensionConstants.vSpacing.xxl.dp
        }

    val shadowScaling: Dp
        get() = when (this) {
            xs -> DimensionConstants.shadowXs.dp
            sm -> DimensionConstants.shadowSm.dp
            md -> DimensionConstants.shadowMd.dp
            lg -> DimensionConstants.shadowLg.dp
            xl -> DimensionConstants.shadowXl.dp
            else -> DimensionConstants.shadowNone.dp
        }
}
