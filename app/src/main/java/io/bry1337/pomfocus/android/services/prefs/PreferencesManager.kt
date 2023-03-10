package io.bry1337.pomfocus.android.services.prefs

import io.bry1337.pomfocus.android.model.UserPreset
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bryan on 3/10/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface PreferencesManager {

    suspend fun setThemePreset(userPreset: UserPreset)
    suspend fun setDarkScheme(isDarkMode: Boolean)

    val themePreset: Flow<String?>
    val isDarkScheme: Flow<Boolean?>
}
