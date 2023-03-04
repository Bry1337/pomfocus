package io.bry1337.pomfocus.android.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.bry1337.pomfocus.android.ui.app.AppState
import io.bry1337.pomfocus.android.ui.developer.DeveloperScreen
import io.bry1337.pomfocus.android.ui.home.HomeScreen

/**
 * Created by Bryan on 3/4/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

enum class MainScene(val route: String) {
    DEVELOPER("developer"),
    HOME("home")
}

fun NavGraphBuilder.mainGraph(
    appState: AppState,
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {}
) {
    composable(MainScene.HOME.route) {
        BackHandler {
            finishActivity()
        }
        HomeScreen(appState = appState, modifier = modifier)
    }

    composable(MainScene.DEVELOPER.route) {
        BackHandler {
            appState.router.navigateUp(it)
        }
        DeveloperScreen()
    }
}
