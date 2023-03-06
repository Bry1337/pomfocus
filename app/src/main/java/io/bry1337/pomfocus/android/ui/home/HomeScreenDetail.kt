package io.bry1337.pomfocus.android.ui.home

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
import io.bry1337.pomfocus.android.ui.components.ActionOutlinedButton
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/6/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun HomeScreenDetail(
    timerValue: String,
    modifier: Modifier = Modifier,
    actionButtonLabel: Int,
    onStartTimer: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val surfaceHeight = configuration.screenHeightDp.dp / 3
    val buttonWidth = configuration.screenWidthDp.dp / 2
    val timerTextColor = MaterialTheme.colorScheme.onBackground
    Surface(
        modifier = modifier
            .height(surfaceHeight)
            .fillMaxWidth()
            .themePaddingAll(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedSurfaceShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = timerValue,
                style = MaterialTheme.typography.displayLarge,
                color = timerTextColor
            )
            Spacer(modifier = Modifier.height(DimensionConstants.hSpacing.lg.dp))
            ActionOutlinedButton(
                modifier = Modifier.width(buttonWidth),
                text = stringResource(id = actionButtonLabel),
                action = onStartTimer
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
            onStartTimer = {},
            actionButtonLabel = R.string.home_screen_start_button
        )
    }
}
