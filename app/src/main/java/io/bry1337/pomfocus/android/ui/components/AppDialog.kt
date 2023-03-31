package io.bry1337.pomfocus.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/31/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean = false,
    action: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val shape = RoundedCornerShape(size = AppSizing.md.roundedCornersScaling)
    if (showDialog) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onDismiss
        ) {
            Surface(modifier = Modifier.themePaddingAll(), shape = shape) {
                Column(modifier = Modifier.themePaddingAll()) {
                    Text(stringResource(id = R.string.app_dialog_message_1))
                    Spacer(Modifier.height(DimensionConstants.vSpacing.md.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        AppTextButton(
                            textLabel = R.string.app_dialog_button_2,
                            action = onDismiss
                        )
                        Spacer(Modifier.width(DimensionConstants.hSpacing.sm.dp))
                        AppTextButton(
                            textLabel = R.string.app_dialog_button_1,
                            action = action
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppDialogPreview() {
    AppTheme {
        AppDialog(showDialog = true)
    }
}
