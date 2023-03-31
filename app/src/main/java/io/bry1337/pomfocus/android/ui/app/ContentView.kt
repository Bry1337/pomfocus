package io.bry1337.pomfocus.android.ui.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

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
        scope.launch {
            appState.router.navigateToScene(viewModel.activeScene)
        }
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
    ContentView() {}
}
