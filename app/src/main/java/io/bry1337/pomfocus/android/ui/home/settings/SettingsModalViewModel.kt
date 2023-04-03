package io.bry1337.pomfocus.android.ui.home.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.extensions.toCamelCase
import io.bry1337.pomfocus.android.model.UserPreset
import io.bry1337.pomfocus.android.services.prefs.PreferencesManager
import io.bry1337.pomfocus.android.ui.theme.ThemeManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bryan on 3/3/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class SettingsModalViewModel @Inject constructor(private val preferencesManager: PreferencesManager) :
    ViewModel() {
    val themePresetNameList = ThemeManager.themePresetNames
    var theme by mutableStateOf(ThemeManager.theme)
        private set

    private val isDarkScheme = theme.isDarkScheme

    var isDarkMode by mutableStateOf(isDarkScheme)
        private set

    var currentSelectedTheme: String? by mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            ThemeManager.themeFlow.collect {
                theme = it
            }
        }
    }

    fun onPresetNameChanged(presetName: String) {
        viewModelScope.launch {
            ThemeManager.onThemePresetNameChanged(presetName)
            flowOf(theme)
                .flowOn(Dispatchers.IO)
                .map { theme.themePreset }
                .collect { themeName ->
                    preferencesManager.setThemePreset(UserPreset(themeName))
                }
            currentSelectedTheme = presetName.toCamelCase()
        }
    }

    fun onDarkModeSwitch(isChecked: Boolean) {
        viewModelScope.launch {
            ThemeManager.onDarkSchemeChanged(isChecked)
            flowOf(isChecked).flowOn(Dispatchers.IO).map {
                it
            }.collect {
                preferencesManager.setDarkScheme(isChecked)
            }
        }
        isDarkMode = isChecked
    }
}
