package io.bry1337.pomfocus.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 3/3/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun ActionOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primary,
    buttonContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = true,
    action: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContainerColor,
            contentColor = buttonContentColor
        ),
        enabled = enabled,
        onClick = action
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                AppIconButton(icon = icon)
            }
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtonPreview() {
    AppTheme {
        ActionOutlinedButton("Log In")
    }
}