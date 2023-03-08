package io.bry1337.pomfocus.android.ui.home

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.RoundedSurfaceShape
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.extensions.themePaddingH
import io.bry1337.pomfocus.android.ui.components.ActionOutlinedButton
import io.bry1337.pomfocus.android.ui.components.AppIconButton
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/6/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun HomeScreenDetail(
    timerValue: String,
    @StringRes pomodoroQuote: Int,
    @StringRes pomodoroTitle: Int,
    modifier: Modifier = Modifier,
    actionButtonLabel: Int,
    isForwardable: Boolean = false,
    onStartTimer: () -> Unit,
    onForward: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val surfaceHeight = configuration.screenHeightDp.dp / 3
    val timerTextColor = MaterialTheme.colorScheme.onBackground
    Surface(
        modifier = modifier
            .height(surfaceHeight)
            .fillMaxWidth()
            .themePaddingAll(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedSurfaceShape,
        elevation = DimensionConstants.shadowSm.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = pomodoroTitle),
                style = MaterialTheme.typography.titleMedium,
                color = timerTextColor
            )
            Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.lg.dp))
            Text(
                text = timerValue,
                style = MaterialTheme.typography.displayLarge,
                color = timerTextColor
            )
            Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.lg.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ActionOutlinedButton(
                    text = stringResource(id = actionButtonLabel),
                    textModifier = Modifier.themePaddingH(AppSizing.sm),
                    action = onStartTimer
                )
                Spacer(modifier = Modifier.width(DimensionConstants.hSpacing.md.dp))
                AnimatedVisibility(visible = isForwardable) {
                    AppIconButton(icon = R.drawable.ic_forward, action = onForward)
                }
            }
            Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.md.dp))
            Text(
                text = stringResource(id = pomodoroQuote),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenDetailPreview() {
    AppTheme {
        HomeScreenDetail(
            "25:00",
            pomodoroTitle = R.string.home_screen_focus_title_1,
            pomodoroQuote = R.string.home_screen_focus_label_1,
            onStartTimer = {},
            actionButtonLabel = R.string.home_screen_start_button,
            onForward = {}
        )
    }
}
