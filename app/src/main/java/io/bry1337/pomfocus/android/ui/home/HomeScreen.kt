package io.bry1337.pomfocus.android.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.RoundedModalShape
import io.bry1337.pomfocus.android.ui.app.AppState
import io.bry1337.pomfocus.android.ui.app.rememberAppState
import io.bry1337.pomfocus.android.ui.components.AppNavBar
import io.bry1337.pomfocus.android.ui.components.AppNavBarItem
import io.bry1337.pomfocus.android.ui.home.settings.SettingsModal
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

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class,
    ExperimentalPermissionsApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    appState: AppState,
    modifier: Modifier = Modifier,
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Move notification permission handling on different composable
    // Fix issue with version capping
    val notificationPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
    if (!notificationPermissionState.status.isGranted && notificationPermissionState.status.shouldShowRationale) {
        // TODO Show rationale message in dialog why notification permission is needed
        // Reference :
        // https://kubiakdev.medium.com/notification-permission-request-on-android-13-part-2-the-implementation-f512239a9bc
        // should run inside onclick trigger
        // notificationPermissionState.launchPermissionRequest()
    }

    val bottomSheetOperation = rememberBottomSheetOperation(
        scope = scope,
        initialBottomSheetDescriptor = HomeScreenBottomSheet.Settings
    )
    val onSettingsPressed = {
        bottomSheetOperation.presentSheet(HomeScreenBottomSheet.Settings)
    }
    val currentPhase = viewModel.pomodoroCurrentIndex
    val taskList = viewModel.taskList
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                bottomSheetOperation.togglePresentation
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetOperation.sheetState,
        sheetContent = {
            when (bottomSheetOperation.sheetDescriptor) {
                HomeScreenBottomSheet.Settings -> SettingsModal(appState = appState)
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
            LazyColumn {
                item {
                    HomeScreenDetail(
                        timerValue = viewModel.formattedRunningTime,
                        pomodoroTitle = viewModel.pomodoroModels[currentPhase].pomodoroTitle,
                        pomodoroQuote = viewModel.pomodoroModels[currentPhase].pomodoroQuote,
                        modifier = Modifier.padding(it),
                        actionButtonLabel = viewModel.pomodoroModels[currentPhase].mainActionButtonLabel,
                        isForwardable = viewModel.pomodoroModels[currentPhase].isForwardable,
                        onStartTimer = viewModel::togglePomodoroMainAction,
                        onForward = viewModel::forwardPomodoroState
                    )
                }

                items(items = taskList, key = {
                        task ->
                    task.id
                }) {
                }
            }
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
        HomeScreen(rememberAppState())
    }
}
