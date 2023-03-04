package io.bry1337.pomfocus.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.bry1337.pomfocus.android.BuildConfig
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingH
import io.bry1337.pomfocus.android.extensions.themeShadow
import io.bry1337.pomfocus.android.ui.app.AppState
import io.bry1337.pomfocus.android.ui.app.rememberAppState
import io.bry1337.pomfocus.android.ui.components.ActionOutlinedButton
import io.bry1337.pomfocus.android.ui.main.MainScene
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/2/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun SettingsModal(
    appState: AppState,
    modifier: Modifier = Modifier,
    viewModel: SettingsModalViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val debugConfig = BuildConfig.DEBUG
    val scope = rememberCoroutineScope()
    val buttonWidth = configuration.screenWidthDp / 2
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(DimensionConstants.hSpacing.md.dp)
            ) {
                Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.md.dp))
                ActionOutlinedButton(
                    modifier = Modifier
                        .width(buttonWidth.dp)
                        .themePaddingH(AppSizing.sm)
                        .themeShadow(AppSizing.sm),
                    text = stringResource(id = R.string.settings_login_button)
                )
                Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.md.dp))
                ActionOutlinedButton(
                    modifier = Modifier
                        .width(buttonWidth.dp)
                        .themePaddingH(AppSizing.sm)
                        .themeShadow(AppSizing.sm),
                    text = stringResource(id = R.string.settings_sign_up_button)
                )
                if (debugConfig) {
                    Spacer(modifier = Modifier.height(DimensionConstants.vSpacing.md.dp))
                    ActionOutlinedButton(
                        modifier = Modifier
                            .width(buttonWidth.dp)
                            .themeShadow(AppSizing.sm),
                        text = stringResource(id = R.string.settings_developer_button),
                        action = {
                            appState.router.navigateToScene(MainScene.DEVELOPER)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsModalPreview() {
    AppTheme {
        SettingsModal(rememberAppState())
    }
}
