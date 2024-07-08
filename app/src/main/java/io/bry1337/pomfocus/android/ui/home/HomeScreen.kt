package io.bry1337.pomfocus.android.ui.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.RoundedModalShape
import io.bry1337.pomfocus.android.extensions.themePaddingV
import io.bry1337.pomfocus.android.model.PomodoroState
import io.bry1337.pomfocus.android.ui.app.AppState
import io.bry1337.pomfocus.android.ui.app.rememberAppState
import io.bry1337.pomfocus.android.ui.components.AppDialog
import io.bry1337.pomfocus.android.ui.components.AppNavBar
import io.bry1337.pomfocus.android.ui.components.AppNavBarItem
import io.bry1337.pomfocus.android.ui.components.AppTaskTextField
import io.bry1337.pomfocus.android.ui.home.settings.SettingsModal
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.android.utils.BottomSheetDescriptor
import io.bry1337.pomfocus.android.utils.rememberBottomSheetOperation
import kotlinx.coroutines.launch

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
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
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
    val keyboardController = LocalSoftwareKeyboardController.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentPomodoroState = viewModel.currentPomodoroState
    val bottomSheetOperation = rememberBottomSheetOperation(
        scope = scope,
        initialBottomSheetDescriptor = HomeScreenBottomSheet.Settings
    )
    val onSettingsPressed = {
        bottomSheetOperation.presentSheet(HomeScreenBottomSheet.Settings)
    }
    val onDrawerPressed: () -> Unit = {
        scope.launch {
            if (drawerState.isOpen) {
                drawerState.close()
            } else {
                drawerState.open()
            }
        }
    }
    val showSaveDialog = viewModel.showSaveOption
    val currentPhase = viewModel.pomodoroCurrentIndex
    val showToastMessage = viewModel.showToastMessage
    val toastMessage = viewModel.toastMessage
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
    LaunchedEffect(showToastMessage) {
        if (showToastMessage) {
            Toast.makeText(
                context,
                context.getString(toastMessage),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        // TODO uncomment when drawer is implemented
//        ModalDrawerSheet {
//        }
    }) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetOperation.sheetState,
            sheetContent = {
                when (bottomSheetOperation.sheetDescriptor) {
                    HomeScreenBottomSheet.Settings -> SettingsModal(appState = appState)
                }
            },
            sheetShape = RoundedModalShape
        ) {
            Scaffold(modifier = modifier.fillMaxSize(), topBar = {
                TopAppbar(onDrawerPressed = onDrawerPressed, onSettingsPressed)
            }) {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
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

                    if (currentPomodoroState.state == PomodoroState.POMODORO_START.state ||
                        currentPomodoroState.state == PomodoroState.POMODORO_RUNNING.state
                    ) {
                        itemsIndexed(items = taskList) { index, task ->
                            AppTaskTextField(
                                modifier = Modifier.themePaddingV(sized = AppSizing.sm),
                                placeholderText = stringResource(id = R.string.home_screen_task_label),
                                currentIndex = index,
                                keyboardOnDone = {
                                    keyboardController?.hide()
                                },
                                deleteAction = {
                                    viewModel.deleteTask(it, task)
                                }
                            )
                        }
                        item {
                            Button(onClick = viewModel::addNewTasks) {
                                Icon(
                                    painterResource(id = R.drawable.ic_plus_add),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                AppDialog(
                    showDialog = showSaveDialog,
                    onDismiss = viewModel::updateSaveDialog,
                    action = {
                        viewModel.saveTasksListed()
                    }
                )
            }
        }
    }
}

@Composable
private fun TopAppbar(onDrawerPressed: () -> Unit, onSettingsPressed: () -> Unit) {
    AppNavBar(leadingItem = {
        // TODO uncomment when navigation options are available
//        AppNavBarItem(icon = R.drawable.ic_menu, action = onDrawerPressed)
    }, trailingItem = {
            AppNavBarItem(icon = R.drawable.ic_settings, action = onSettingsPressed)
        })
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(rememberAppState())
    }
}
