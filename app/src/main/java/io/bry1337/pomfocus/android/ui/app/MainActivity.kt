package io.bry1337.pomfocus.android.ui.app

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ContentView {
                finish()
            }
        }
    }
}
