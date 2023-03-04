package io.bry1337.pomfocus.android.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themeButton
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.extensions.themePaddingV
import io.bry1337.pomfocus.android.extensions.themeShadow
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing

/**
 * Created by Bryan on 3/1/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    @DrawableRes icon: Int? = null,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    shape: Shape? = null,
    content: @Composable (() -> Unit)? = null,
    action: () -> Unit = {}
) {
    if (icon != null || content != null) {
        val addedShape = shape ?: RoundedCornerShape(size = sized.roundedCornersScaling)
        val buttonContent: @Composable () -> Unit = {
            IconButton(
                modifier = modifier.themeButton(sized),
                onClick = action
            ) {
                if (content != null) {
                    content()
                } else if (icon != null) {
                    Icon(painterResource(id = icon), contentDescription = null, tint = iconColor)
                }
            }
        }
        if (backgroundColor == Color.Transparent) {
            buttonContent()
        } else {
            Surface(
                modifier = modifier.themeShadow(),
                shape = addedShape,
                color = backgroundColor,
                content = buttonContent
            )
        }
    }
}

@Preview("default", showBackground = true)
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppIconButtonPreview() {
    AppTheme {
        Column(
            Modifier
                .themePaddingAll(AppSizing.xl)
        ) {
            AppIconButton(icon = R.drawable.ic_menu)
            Spacer(modifier = Modifier.themePaddingV())
            AppIconButton(icon = R.drawable.ic_settings)
        }
    }
}
