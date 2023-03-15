package io.bry1337.pomfocus.android.ui.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.model.UserPreset
import io.bry1337.pomfocus.android.services.prefs.PreferencesManager
import io.bry1337.pomfocus.android.ui.theme.ThemeManager
import io.bry1337.pomfocus.theme.DurationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class ContentViewModel @Inject constructor(preferencesManager: PreferencesManager) : ViewModel() {
    val themeFlow = ThemeManager.themeFlow
    var activeScene by mutableStateOf(AppScene.SPLASH)
    var isActive by mutableStateOf(false)

    init {
        viewModelScope.launch {
            preferencesManager.themePreset.flowOn(Dispatchers.Default)
                .map { dataString ->
                    dataString?.let {
                        val userPreset = Json.decodeFromString<UserPreset>(dataString)
                        ThemeManager.onThemePresetNameChanged(userPreset.themePreset.name)
                    }
                }.flatMapLatest {
                    preferencesManager.isDarkScheme.flowOn(Dispatchers.Default)
                        .map { isDarkScheme ->
                            isDarkScheme?.let {
                                ThemeManager.onDarkSchemeChanged(it)
                            }
                        }
                }.collect {
                    proceedToNextRoute()
                }
        }
    }

    private suspend fun proceedToNextRoute() {
        snapshotFlow {
            isActive
        }.filter {
            it
        }.onStart {
            delay(DurationConstants.splashDurationSeconds.toLong() * 1000)
        }.map {
            AppScene.MAIN
        }.collect {
            activeScene = it
        }
    }
}
