package io.bry1337.pomfocus.android.ui.app

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun ContentView(
    viewModel: ContentViewModel = hiltViewModel(),
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    finishActivity: () -> Unit
) {
    val appState = rememberAppState()
    val currentFinishActivity by rememberUpdatedState(finishActivity)
    val theme = viewModel.themeFlow.collectAsState().value

    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.isActive = true
            } else if (event == Lifecycle.Event.ON_STOP) {
                viewModel.isActive = false
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AppTheme(theme = theme) {
        AppGraph(appState = appState, finishActivity = currentFinishActivity)
    }
}

@Preview
@Composable
fun ContentViewPreview() {
    ContentView() {
    }
}
