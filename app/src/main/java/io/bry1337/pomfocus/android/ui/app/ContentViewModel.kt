package io.bry1337.pomfocus.android.ui.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.model.UserPreset
import io.bry1337.pomfocus.android.services.prefs.PreferencesManager
import io.bry1337.pomfocus.android.ui.theme.ThemeManager
import io.bry1337.pomfocus.theme.DurationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ContentViewModel @Inject constructor(preferencesManager: PreferencesManager) : ViewModel() {
    val themeFlow = ThemeManager.themeFlow
    var activeScene by mutableStateOf(AppScene.SPLASH)
    var isActive by mutableStateOf(false)
    var isThemeDone by mutableStateOf(false)
    var isSchemeDone by mutableStateOf(false)

    init {
        viewModelScope.launch {
            // Dispatch to Main thread to be able to update the mutable state of variable
            // after running the flow in IO Dispatcher
            withContext(Dispatchers.Main) {
                preferencesManager.themePreset.flowOn(Dispatchers.IO)
                    .takeWhile { !isThemeDone }.collect { dataString ->
                        dataString?.let {
                            isThemeDone = true
                            val userPreset = Json.decodeFromString<UserPreset>(dataString)
                            ThemeManager.onThemePresetNameChanged(userPreset.themePreset.name)
                        }
                    }
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                preferencesManager.isDarkScheme.flowOn(Dispatchers.IO).takeWhile { !isSchemeDone }.map { isDarkScheme ->
                    isDarkScheme?.let {
                        ThemeManager.onDarkSchemeChanged(isDarkScheme)
                    }
                }.collect {
                    isSchemeDone = true
                    delay(DurationConstants.splashDurationSeconds * 1000L)
                    activeScene = AppScene.MAIN
                }
            }
        }
    }
}
