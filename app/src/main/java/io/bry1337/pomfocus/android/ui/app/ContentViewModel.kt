package io.bry1337.pomfocus.android.ui.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.ui.theme.ThemeManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class ContentViewModel @Inject constructor() : ViewModel() {
    val themeFlow = ThemeManager.themeFlow
    var activeScene by mutableStateOf(AppScene.SPLASH)
    var isActive by mutableStateOf(false)

    init {
//        viewModelScope.launch {
//            snapshotFlow {
//                isActive
//            }.filter {
//                it
//            }.onStart {
//                delay(DurationConstants.splashDurationSeconds.toLong() * 1000)
//            }.map {
//                AppScene.MAIN
//            }.collect {
//                activeScene = it
//            }
//        }
    }
}
