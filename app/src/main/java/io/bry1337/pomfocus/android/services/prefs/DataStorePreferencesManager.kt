package io.bry1337.pomfocus.android.services.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.bry1337.pomfocus.android.model.UserPreset
import io.bry1337.pomfocus.preferences.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Created by Bryan on 3/10/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

private const val APP_STATE_FILE: String = "app_state"

// Extension of context for now
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_STATE_FILE)

class DataStorePreferencesManager @Inject constructor(private val appContext: Context) :
    PreferencesManager {

    companion object {
        private val THEME_PRESET = stringPreferencesKey(PreferencesKeys.themeSelected)
        private val DARK_SCHEME = booleanPreferencesKey(PreferencesKeys.isDarkScheme)
    }

    override suspend fun setThemePreset(userPreset: UserPreset) {
        val dataAsString = Json.encodeToString(userPreset)
        appContext.dataStore.edit { preference ->
            preference[THEME_PRESET] = dataAsString
        }
    }

    override suspend fun setDarkScheme(isDarkMode: Boolean) {
        appContext.dataStore.edit { preference ->
            preference[DARK_SCHEME] = isDarkMode
        }
    }

    // This val returns flow that should be handled on the viewmodel of
    // the calling method
    override val themePreset: Flow<String?> =
        appContext.dataStore.data.map { preference ->
            preference[THEME_PRESET]
        }

    // This val returns flow that should be handled on the viewmodel of
    // the calling method
    override val isDarkScheme: Flow<Boolean?> =
        appContext.dataStore.data.map { preference ->
            preference[DARK_SCHEME]
        }
}
