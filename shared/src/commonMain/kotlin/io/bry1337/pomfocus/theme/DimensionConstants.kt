package io.bry1337.pomfocus.theme

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

/*
See values at:
https://android.googlesource.com/platform/frameworks/base/+/master/core/res/res/values/dimens.xml
https://material.io/components/snackbars#specs
*/

object DimensionConstants {
    // Buttons
    const val buttonSizeXs = 32.0f
    const val buttonSizeSm = 36.0f
    const val buttonSizeMd = 40.0f
    const val buttonSizeLg = 44.0f
    const val buttonSizeXl = 48.0f

    // Switch
    const val switchSizeMd = 32.0f
    const val switchSizeLg = 36.0f
    const val switchSizeXl = 40.0f

    // Pagers
    const val pagingIndicatorSize = 6.0f
    const val pagingIndicatorSelectedWidth = 30.0f

    // Offset
    const val bottomNavigationHideLabel = 18

    // Shape
    // https://m3.material.io/styles/shape/shape-scale-tokens
    const val roundedCornersNone = 0
    const val roundedCornersXs = 4
    const val roundedCornersSm = 8
    const val roundedCornersMd = 12
    const val roundedCornersLg = 16
    const val roundedCornersXl = 28
    const val roundedCornersFull = -1

    // Spacing
    val vSpacing = Spacing.vSpacingScaled()
    val hSpacing = Spacing.hSpacingScaled()
    const val spacingNone = 0
    const val spacingMin = 2

    // List Items
    const val listItemMinHeight = 40

    // Toast
    const val toastBottomOffset = 48
    const val toastCornerRadius = 4
    const val toastMaxWidth = 344
    const val toastMinHeight = 48
    const val toastTextSize = 14
    const val toastElevation = 2

    // Avatar
    const val avatarImageSizeXs = 20
    const val avatarImageSizeSm = 32
    const val avatarImageSizeMd = 48
    const val avatarImageSizeLg = 56
    const val avatarImageSizeXl = 64
    const val avatarImageSizeXxl = 72

    // Icons
    const val iconSizeXs = 12
    const val iconSizeSm = 16
    const val iconSizeMd = 24
    const val iconSizeLg = 32
    const val iconSizeXl = 40
    const val iconSizeXxl = 48

    fun avatarStrokeSizeForImageSize(size: Int): Int {
        if (size <= avatarImageSizeSm) {
            return 2
        }
        if (size <= avatarImageSizeLg) {
            return 3
        }
        if (size <= avatarImageSizeXxl) {
            return 4
        }
        return 5
    }

    // App Bars
    const val bottomBarSize = 64
    const val navigationBarSize = 64

    // Button Stroke
    const val buttonStroke = 1

    // Bottom Bar Padding
    val bottomContentPadding = bottomBarSize + vSpacing.lg
}
