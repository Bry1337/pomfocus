package io.bry1337.pomfocus.android.ui.theme

import io.bry1337.pomfocus.theme.Theme
import io.bry1337.pomfocus.theme.ThemePreset
import io.bry1337.pomfocus.theme.ThemeProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

object ThemeManager {
    private val _themeFlow = MutableStateFlow(ThemeProvider.get())
    val themeFlow = _themeFlow.asStateFlow()
    val theme: Theme
        get() = _themeFlow.value
    val themePresetNames = ThemePreset.NAMES

    suspend fun onIsDarkSchemeChanged(isDarkScheme: Boolean) {
        _themeFlow.emit(ThemeProvider.get(preset = theme.themePreset, isDarkScheme = isDarkScheme))
    }

    suspend fun onThemePresetNameChanged(presetName: String) {
        _themeFlow.emit(
            ThemeProvider.get(
                presetName = presetName,
                isDarkScheme = theme.isDarkScheme
            )
        )
    }
}
