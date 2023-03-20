package io.bry1337.pomfocus.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
    textModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primary,
    buttonContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    @DrawableRes icon: Int? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.onBackground,
    iconModifier: Modifier = Modifier,
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
                AppIconButton(icon = icon, modifier = iconModifier, backgroundColor = backgroundColor)
            }
            Text(text = text, modifier = textModifier)
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
