package io.bry1337.pomfocus.android.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppNavBar(
    modifier: Modifier = Modifier,
    leadingItem: @Composable () -> Unit = {},
    trailingItem: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .statusBarsPadding()
            .themePaddingAll(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DimensionConstants.hSpacing.md.dp)
    ) {
        leadingItem()
        Row(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            title()
        }
        trailingItem()
    }
}

@Composable
fun AppNavBarItem(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    @DrawableRes icon: Int? = null,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: (@Composable () -> Unit)? = null,
    action: () -> Unit = {}
) {
    AppIconButton(
        modifier = modifier,
        sized = sized,
        icon = icon,
        iconColor = iconColor,
        backgroundColor = backgroundColor,
        content = content,
        action = action
    )
}

@Preview("default", showBackground = true)
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavBarPreview() {
    AppTheme {
        AppNavBar(
            leadingItem = {
                AppNavBarItem(icon = R.drawable.ic_menu)
            },
            trailingItem = {
                AppNavBarItem(icon = R.drawable.ic_settings)
            }
        )
    }
}
