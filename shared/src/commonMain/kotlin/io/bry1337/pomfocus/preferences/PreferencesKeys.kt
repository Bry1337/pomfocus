package io.bry1337.pomfocus.preferences

/**
 * Created by Bryan on 3/10/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

private fun prefix(key: String) = "pf.$key"

object PreferencesKeys {
    val themeSelected = prefix("themeSelected")
    val isDarkScheme = prefix("isDarkMode")
}
