package io.bry1337.pomfocus.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.bry1337.pomfocus.android.extensions.appShapes
import io.bry1337.pomfocus.android.extensions.materialColorScheme
import io.bry1337.pomfocus.android.extensions.materialTypography
import io.bry1337.pomfocus.theme.Theme

/**
 * Created by Bryan on 2/28/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppTheme(theme: Theme = ThemeManager.theme, content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !theme.colors.isDarkScheme
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }

    MaterialTheme(
        colorScheme = theme.materialColorScheme(),
        typography = theme.materialTypography(),
        shapes = theme.appShapes(),
        content = content
    )
}
