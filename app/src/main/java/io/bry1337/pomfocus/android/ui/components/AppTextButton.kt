package io.bry1337.pomfocus.android.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 3/31/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppTextButton(
    @StringRes textLabel: Int,
    modifier: Modifier = Modifier,
    action: () -> Unit = {}
) {
    val buttonColors =
        ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
    TextButton(modifier = modifier, onClick = action, colors = buttonColors) {
        Text(stringResource(id = textLabel))
    }
}

@Preview
@Composable
fun AppTextButtonPreview() {
    AppTheme {
        AppTextButton(R.string.app_dialog_button_1)
    }
}
