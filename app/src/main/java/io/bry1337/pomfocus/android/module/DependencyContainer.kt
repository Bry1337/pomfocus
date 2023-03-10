package io.bry1337.pomfocus.android.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.bry1337.pomfocus.android.services.prefs.DataStorePreferencesManager
import io.bry1337.pomfocus.android.services.prefs.PreferencesManager
import javax.inject.Singleton

/**
 * Created by Bryan on 3/10/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@InstallIn(SingletonComponent::class)
@Module
object DependencyContainer {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): PreferencesManager =
        DataStorePreferencesManager(appContext)
}
