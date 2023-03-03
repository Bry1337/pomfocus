package io.bry1337.pomfocus.android.ui.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.RoundedModalShape
import io.bry1337.pomfocus.android.ui.components.AppNavBar
import io.bry1337.pomfocus.android.ui.components.AppNavBarItem
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.BottomSheetDescriptor
import io.bry1337.pomfocus.android.utils.rememberBottomSheetOperation

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalMaterialApi::class)
private enum class HomeScreenBottomSheet : BottomSheetDescriptor {
    Settings;

    override val launchPresentation: ModalBottomSheetValue
        get() = when (this) {
            Settings -> ModalBottomSheetValue.Expanded
            else -> ModalBottomSheetValue.HalfExpanded
        }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val bottomSheetOperation = rememberBottomSheetOperation(
        scope = scope,
        initialBottomSheetDescriptor = HomeScreenBottomSheet.Settings
    )
    val onSettingsPressed = {
        bottomSheetOperation.presentSheet(HomeScreenBottomSheet.Settings)
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetOperation.sheetState,
        sheetContent = {
            when (bottomSheetOperation.sheetDescriptor) {
                HomeScreenBottomSheet.Settings -> SettingsModal()
            }
        },
        sheetShape = RoundedModalShape
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppbar(onSettingsPressed)
            }
        ) {
        }
    }
}

@Composable
private fun TopAppbar(onSettingsPressed: () -> Unit) {
    AppNavBar(
        leadingItem = {
            AppNavBarItem(icon = R.drawable.ic_menu)
        },
        trailingItem = {
            AppNavBarItem(icon = R.drawable.ic_settings, action = onSettingsPressed)
        }
    )
}

@Preview("default", showBackground = true)
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}
