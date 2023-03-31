package io.bry1337.pomfocus.android.ui.app

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.bry1337.pomfocus.android.ui.main.MainScene
import io.bry1337.pomfocus.android.ui.main.mainGraph
import io.bry1337.pomfocus.android.ui.splash.SplashScreen

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

enum class AppScene(val route: String) {
    SPLASH("splash"),
    MAIN("main")
}

@Composable
fun AppGraph(appState: AppState, modifier: Modifier = Modifier, finishActivity: () -> Unit = {}) {
    NavHost(navController = appState.navController, startDestination = AppScene.SPLASH.route) {
        composable(AppScene.SPLASH.route) {
            BackHandler {
                finishActivity()
            }
            SplashScreen(modifier)
        }

        navigation(route = AppScene.MAIN.route, startDestination = MainScene.HOME.route) {
            mainGraph(
                appState = appState,
                modifier = modifier,
                finishActivity = finishActivity
            )
        }
    }
}
