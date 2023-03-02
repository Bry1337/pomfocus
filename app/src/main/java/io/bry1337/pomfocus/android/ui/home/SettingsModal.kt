package io.bry1337.pomfocus.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/2/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun SettingsModal(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {
            Column(
                modifier = modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(DimensionConstants.hSpacing.md.dp)
            ) {
                Text("Hello World")
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
