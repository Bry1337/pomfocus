package io.bry1337.pomfocus.theme

import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeLg
import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeMd
import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeSm
import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeXl
import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeXs
import io.bry1337.pomfocus.theme.DimensionConstants.buttonSizeXxl

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface Sizing {
    val xs: Int
    val sm: Int
    val md: Int
    val lg: Int
    val xl: Int
    val xxl: Int

    companion object {
        fun buttonSizingScaled(multiplier: Float = 1f): Sizing = SizingImpl(
            xs = (buttonSizeXs * multiplier).toInt(),
            sm = (buttonSizeSm * multiplier).toInt(),
            md = (buttonSizeMd * multiplier).toInt(),
            lg = (buttonSizeLg * multiplier).toInt(),
            xl = (buttonSizeXl * multiplier).toInt(),
            xxl = (buttonSizeXxl * multiplier).toInt()
        )
    }
}

internal data class SizingImpl(
    override val xs: Int,
    override val sm: Int,
    override val md: Int,
    override val lg: Int,
    override val xl: Int,
    override val xxl: Int
) : Sizing
