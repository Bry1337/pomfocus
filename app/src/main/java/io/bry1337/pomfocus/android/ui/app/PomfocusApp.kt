package io.bry1337.pomfocus.android.ui.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.bry1337.pomfocus.theme.AppContext

/**
 * Created by Bryan on 3/3/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltAndroidApp
class PomfocusApp : Application() {
    init {
        AppContext.registerContextProvider {
            this
        }
    }
}
