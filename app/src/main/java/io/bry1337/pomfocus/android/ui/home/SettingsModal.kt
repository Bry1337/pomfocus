package io.bry1337.pomfocus.android.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.bry1337.pomfocus.android.ui.components.ActionOutlinedButton
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/2/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun SettingsModal(
    modifier: Modifier = Modifier,
    viewModel: SettingsModalViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(DimensionConstants.hSpacing.md.dp)
            ) {
                ActionOutlinedButton(
                    text = "Dark Mode", action =
                    viewModel::onThemeSchemeChanged
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsModalPreview() {
    AppTheme {
        SettingsModal()
    }
}
