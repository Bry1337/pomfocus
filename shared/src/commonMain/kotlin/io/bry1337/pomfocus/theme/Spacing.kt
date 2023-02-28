package io.bry1337.pomfocus.theme

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface Spacing {
    val xs: Int
    val sm: Int
    val md: Int
    val lg: Int
    val xl: Int
    val xxl: Int

    companion object {
        fun vSpacingScaled(
            multiplier: Float = 1f
        ): Spacing = SpacingImpl(
            xs = (4 * multiplier).toInt(),
            sm = (8 * multiplier).toInt(),
            md = (12 * multiplier).toInt(),
            lg = (16 * multiplier).toInt(),
            xl = (20 * multiplier).toInt(),
            xxl = (24 * multiplier).toInt()
        )

        fun hSpacingScaled(
            multiplier: Float = 1f
        ): Spacing = SpacingImpl(
            xs = (4 * multiplier).toInt(),
            sm = (10 * multiplier).toInt(),
            md = (16 * multiplier).toInt(),
            lg = (20 * multiplier).toInt(),
            xl = (24 * multiplier).toInt(),
            xxl = (32 * multiplier).toInt()
        )
    }
}

internal data class SpacingImpl(
    override val xs: Int,
    override val sm: Int,
    override val md: Int,
    override val lg: Int,
    override val xl: Int,
    override val xxl: Int
) : Spacing
