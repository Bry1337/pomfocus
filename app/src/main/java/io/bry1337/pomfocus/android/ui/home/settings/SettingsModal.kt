package io.bry1337.pomfocus.android.ui.home.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import io.bry1337.pomfocus.theme.DimensionConstants.hSpacing

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
    val dropDownHeight = (configuration.screenHeightDp / 3).dp
    val themeList = viewModel.themePresetNameList
    val isDarkMode = viewModel.isDarkMode
    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    val onDropDownExpand: () -> Unit = {
        dropDownMenuExpanded = !dropDownMenuExpanded
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().themePaddingH(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ThemeSelection(
                        onDropDownExpand,
                        dropDownMenuExpanded,
                        themeList,
                        viewModel,
                        isDarkMode,
                        dropDownHeight
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MainButtons(buttonWidth, debugConfig, appState)
                }
            }
        }
    }
}

@Composable
private fun ThemeSelection(
    onDropDownExpand: () -> Unit,
    dropDownMenuExpanded: Boolean,
    themeList: List<String>,
    viewModel: SettingsModalViewModel,
    isDarkMode: Boolean,
    dropDownHeight: Dp
) {
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(hSpacing.sm.dp)
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        onDropDownExpand()
                    },
                text = stringResource(id = R.string.settings_theme_button),
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        onDropDownExpand()
                    },
                painter = painterResource(id = R.drawable.ic_dropdown),
                contentDescription = null
            )
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = onDropDownExpand,
                modifier = Modifier.height(dropDownHeight)
            ) {
                themeList.forEach {
                    DropdownMenuItem(text = {
                        Text(it)
                    }, onClick = {
                            onDropDownExpand()
                            viewModel.onPresetNameChanged(it)
                        })
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(hSpacing.sm.dp)
    ) {
        Text(
            stringResource(id = R.string.settings_dark_mode_button),
            style = MaterialTheme.typography.titleMedium
        )
        Switch(
            checked = isDarkMode,
            onCheckedChange = viewModel::onDarkModeSwitch
        )
    }
}

@Composable
private fun MainButtons(
    buttonWidth: Int,
    debugConfig: Boolean,
    appState: AppState
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

@Preview(showBackground = true)
@Composable
fun SettingsModalPreview() {
    AppTheme {
        SettingsModal(rememberAppState())
    }
}
