package io.bry1337.pomfocus.android.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import io.bry1337.pomfocus.android.utils.AppSizing

/**
 * Created by Bryan on 3/2/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

val RoundedModalShape = RoundedCornerShape(
    topStart = AppSizing.md.roundedCornersScaling,
    topEnd = AppSizing.md.roundedCornersScaling
)
