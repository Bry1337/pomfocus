package io.bry1337.pomfocus.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 3/20/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppAddTaskButton(
    modifier: Modifier = Modifier,
    label: String = "",
    @DrawableRes icon: Int = R.drawable.ic_plus_add,
    backgroundColor: Color = Color.Transparent,
    action: () -> Unit = {}
) {
    ActionOutlinedButton(
        modifier = modifier,
        text = label,
        icon = icon,
        backgroundColor = backgroundColor,
        action = action
    )
}

@Preview
@Composable
fun AppAddTaskButtonPreview() {
    AppTheme {
        AppAddTaskButton(label = "Text")
    }
}
