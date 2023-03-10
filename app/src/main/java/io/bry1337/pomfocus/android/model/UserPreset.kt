package io.bry1337.pomfocus.android.model

import io.bry1337.pomfocus.theme.ThemePreset
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Bryan on 3/10/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Serializable
data class UserPreset(
    @SerialName("theme_preset")
    val themePreset: ThemePreset
)
