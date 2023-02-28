package io.bry1337.pomfocus.android.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

// Modeled similar to:
// https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/JetsnackAppState.kt
@Stable
class AppState(
    val navController: NavHostController,
    val router: AppRouter,
    coroutineScope: CoroutineScope
)

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    router: AppRouter = remember(navController) {
        AppRouter(navController)
    },
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState = remember(navController, coroutineScope) {
    AppState(
        navController = navController,
        router = router,
        coroutineScope = coroutineScope
    )
}
