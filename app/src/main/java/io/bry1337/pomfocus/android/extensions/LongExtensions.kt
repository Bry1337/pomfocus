package io.bry1337.pomfocus.android.extensions

import androidx.compose.ui.graphics.Color

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

fun Long.argbToColor(): Color = Color(color = this)
