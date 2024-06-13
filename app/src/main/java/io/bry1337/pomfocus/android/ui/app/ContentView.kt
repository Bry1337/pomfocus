package io.bry1337.pomfocus.android.ui.app

import android.Manifest
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import io.bry1337.pomfocus.android.ui.components.NotificationPermissionRequest
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalPermissionsApi::class)
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
    // Move notification permission handling on different composable
    // Fix issue with version capping
    val notificationPermissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

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
            if (!notificationPermissionState.status.isGranted) {
                NotificationPermissionRequest(notificationPermissionState = notificationPermissionState)
            }
        }
    }
}

@Preview
@Composable
fun ContentViewPreview() {
    ContentView() {}
}
