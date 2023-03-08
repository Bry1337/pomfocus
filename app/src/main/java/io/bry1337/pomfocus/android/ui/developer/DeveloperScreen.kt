package io.bry1337.pomfocus.android.ui.developer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.ui.components.ActionOutlinedButton
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/4/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun DeveloperScreen(
    modifier: Modifier = Modifier,
    viewModel: DeveloperScreenViewModel = hiltViewModel()
) {
    val isDarkMode = viewModel.isDarkMode
    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    val onDropDownExpand: () -> Unit = {
        dropDownMenuExpanded = !dropDownMenuExpanded
    }
    Scaffold {
        LazyVerticalGrid(
            modifier = modifier
                .padding(it)
                .themePaddingAll(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(AppSizing.sm.paddingScalingV),
            horizontalArrangement = Arrangement.spacedBy(AppSizing.sm.paddingScalingH)
        ) {
            gridTitleComponent(
                themeName = viewModel.themePresetNameList,
                isDarkMode = isDarkMode,
                dropDownExpanded = dropDownMenuExpanded,
                onDropDownExpand = onDropDownExpand,
                onThemeChange = viewModel::onPresetNameChanged,
                onCheckChanged = viewModel::onDarkModeSwitch
            )
            outlinedButton()
            elevatedButton()
            filledTonalButton()
            colorContainers()
        }
    }
}

private fun LazyGridScope.gridTitleComponent(
    themeName: List<String>,
    isDarkMode: Boolean,
    dropDownExpanded: Boolean,
    onDropDownExpand: () -> Unit,
    onThemeChange: (String) -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    item {
        Box(modifier = Modifier.themePaddingAll()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            onDropDownExpand()
                        },
                    text = "Theme",
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
                    expanded = dropDownExpanded,
                    onDismissRequest = onDropDownExpand
                ) {
                    themeName.forEach {
                        DropdownMenuItem(text = {
                            Text(it)
                        }, onClick = {
                                onDropDownExpand()
                                onThemeChange(it)
                            })
                    }
                }
            }
        }
    }
    item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Dark Mode", style = MaterialTheme.typography.titleMedium)
            Switch(checked = isDarkMode, onCheckedChange = onCheckChanged)
        }
    }
}

private fun LazyGridScope.outlinedButton() {
    item {
        ActionOutlinedButton(text = "Outlined")
    }
    item {
        ActionOutlinedButton(text = "Outlined", enabled = false)
    }
}

private fun LazyGridScope.elevatedButton() {
    item {
        ElevatedButton(onClick = {}) {
            Text("Elevated")
        }
    }

    item {
        ElevatedButton(onClick = {}, enabled = false) {
            Text("Elevated")
        }
    }
}

private fun LazyGridScope.filledTonalButton() {
    item {
        FilledTonalButton(onClick = {}) {
            Text("Filled Tonal")
        }
    }

    item {
        FilledTonalButton(onClick = {}, enabled = false) {
            Text("Filled Tonal")
        }
    }
}

private fun LazyGridScope.colorContainers() {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text("onPrimary", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("onPrimaryContainer", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary),
            contentAlignment = Alignment.Center
        ) {
            Text("inversePrimary")
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text("onSecondary", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("onSecondaryContainer", color = MaterialTheme.colorScheme.onSecondaryContainer)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center
        ) {
            Text("onTertiary", color = MaterialTheme.colorScheme.onTertiary)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.tertiaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("onTertiaryContainer", color = MaterialTheme.colorScheme.onTertiaryContainer)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text("onBackground", color = MaterialTheme.colorScheme.onBackground)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text("onSurface", color = MaterialTheme.colorScheme.onSurface)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text("onSurfaceVariant", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface),
            contentAlignment = Alignment.Center
        ) {
            Text("inverseOnSurface", color = MaterialTheme.colorScheme.inverseOnSurface)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.surfaceTint),
            contentAlignment = Alignment.Center
        ) {
            Text("surfaceTint")
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center
        ) {
            Text("onError", color = MaterialTheme.colorScheme.onError)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.errorContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("onErrorContainer", color = MaterialTheme.colorScheme.onErrorContainer)
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DimensionConstants.boxHeightXxl.dp)
                .background(color = MaterialTheme.colorScheme.outline),
            contentAlignment = Alignment.Center
        ) {
            Text("outlineVariant", color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeveloperScreenPreview() {
    AppTheme {
        DeveloperScreen()
    }
}
