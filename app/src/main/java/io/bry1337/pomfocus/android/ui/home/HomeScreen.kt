package io.bry1337.pomfocus.android.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.ui.components.AppNavBar
import io.bry1337.pomfocus.android.ui.components.AppNavBarItem
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppbar()
        }
    ) {
    }
}

@Composable
private fun TopAppbar() {
    AppNavBar(
        leadingItem = {
            AppNavBarItem(icon = R.drawable.ic_menu)
        },
        trailingItem = {
            AppNavBarItem(icon = R.drawable.ic_settings)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}
