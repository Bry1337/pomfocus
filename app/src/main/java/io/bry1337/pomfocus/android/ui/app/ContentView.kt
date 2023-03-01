package io.bry1337.pomfocus.android.ui.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
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

    LaunchedEffect(key1 = viewModel.activeScene) {
        appState.router.navigateToScene(viewModel.activeScene)
    }

    AppTheme(theme = theme) {
        Scaffold {
            AppGraph(
                appState = appState,
                modifier = Modifier.padding(it),
                finishActivity = currentFinishActivity
            )
        }
    }
}

@Preview
@Composable
fun ContentViewPreview() {
    ContentView() {
    }
}
