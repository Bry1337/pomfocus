package io.bry1337.pomfocus.android.ui.app

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

// Refactored from:
// https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/JetsnackAppState.kt

class AppRouter(private val navController: NavHostController) {
    // Navigation state
    val currentRoute: String?
        get() = navController.currentDestination?.route

    // In order to discard duplicated navigation events, we check the Lifecycle
    fun navigateUp(backStackEntry: NavBackStackEntry) {
        if (backStackEntry.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }

    fun navigateToScene(scene: AppScene) {
        // On navigating to app scenes i.e. splash/bootstrap or main
        // - Pop up to the start destination of the graph and don't save state since
        //   no route hierarchy should be maintained
        // - launchSingleTop to avoid multiple copies of the same destination
        // - Don't restore any state
        if (scene.route != currentRoute) {
            navController.navigate(scene.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                    saveState = false
                }
                launchSingleTop = true
                restoreState = false
            }
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a navigation event.
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
