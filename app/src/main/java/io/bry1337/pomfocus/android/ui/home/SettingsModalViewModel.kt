package io.bry1337.pomfocus.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.ui.theme.ThemeManager
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bryan on 3/3/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class SettingsModalViewModel @Inject constructor()  : ViewModel() {
    val themePresetNameList = ThemeManager.themePresetNames
    val themePresetName
        get() = theme.themePreset.name
    val isDarkScheme
        get() = theme.isDarkScheme
    var theme by mutableStateOf(ThemeManager.theme)
        private set

    init {
        viewModelScope.launch {
            ThemeManager.themeFlow.collect {
                theme = it
            }
        }
    }

    fun onThemeSchemeChanged() {
        viewModelScope.launch {
            ThemeManager.onDarkSchemeChanged(!isDarkScheme)
        }
    }
}