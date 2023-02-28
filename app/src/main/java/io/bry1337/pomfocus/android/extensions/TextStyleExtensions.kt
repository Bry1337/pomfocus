package io.bry1337.pomfocus.android.extensions

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import io.bry1337.pomfocus.theme.TextStyle
import androidx.compose.ui.text.TextStyle as MaterialTextStyle

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

fun TextStyle.toMaterialStyle(): MaterialTextStyle =
    MaterialTextStyle(
        fontSize = this.fontSize.sp,
        lineHeight = this.lineHeight?.sp ?: TextUnit.Unspecified,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(this.fontWeight.weight),
        letterSpacing = this.letterSpacing.sp
    )
