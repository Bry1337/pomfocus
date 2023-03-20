package io.bry1337.pomfocus.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingEnd
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 3/17/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppFilledTextField(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    placeholder: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(size = sized.roundedCornersScaling),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardOnDone: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
        cursorColor = textColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    var textValue by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = {
        textValue = it
    }
    TextField(
        value = textValue, onValueChange = onValueChange, modifier = modifier,
        placeholder = placeholder, label = label,
        trailingIcon = trailingIcon,
        isError = isError,
        leadingIcon = leadingIcon,
        maxLines = maxLines,
        minLines = minLines,
        enabled = enabled,
        shape = shape,
        colors = colors,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            keyboardOnDone(textValue)
            keyboardController?.hide()
        })
    )
}

@Composable
fun AppRowTextField(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    placeholder: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(size = sized.roundedCornersScaling),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
        cursorColor = textColor
    )
    var textValue by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = {
        textValue = it
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DimensionConstants.hSpacing.md.dp)
    ) {
        if (leadingIcon != null) {
            leadingIcon()
        }
        Row(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = textValue, onValueChange = onValueChange,
                placeholder = placeholder, label = label,
                isError = isError,
                maxLines = maxLines,
                minLines = minLines,
                shape = shape,
                colors = colors
            )
        }
        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}

@Composable
fun AppTaskTextField(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    isError: Boolean = false,
    placeholderText: String? = null,
    labelText: String? = null,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(size = sized.roundedCornersScaling),
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardOnDone: (String) -> Unit = {},
    deleteAction: () -> Unit = {}
) {
    val deleteIcon: @Composable () -> Unit = {
        AppIconButton(
            icon = R.drawable.ic_delete,
            action = deleteAction,
            modifier = Modifier.themePaddingEnd(sized = AppSizing.xs)
        )
    }
    val placeholder: @Composable () -> Unit = {
        Text(placeholderText ?: "")
    }
    var label: @Composable (() -> Unit)? = null
    if (labelText != null) {
        label = {
            Text(labelText)
        }
    }
    AppFilledTextField(
        modifier = modifier,
        trailingIcon = deleteIcon,
        sized = sized,
        singleLine = true,
        shape = shape,
        isError = isError,
        enabled = enabled,
        placeholder = placeholder,
        label = label,
        keyboardOptions = keyboardOptions,
        keyboardOnDone = keyboardOnDone
    )
}

@Preview(showBackground = true)
@Composable
fun AppFilledTextFieldPreview() {
    AppTheme {
        Box(modifier = Modifier.background(color = Color.White)) {
            AppFilledTextField(
                leadingIcon = {
                    AppIconButton(icon = R.drawable.ic_menu)
                },
                placeholder = {
                    Text("placeholder")
                },
                trailingIcon = {
                    AppIconButton(icon = R.drawable.ic_settings)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppRowTextFieldPreview() {
    AppTheme {
        Box(modifier = Modifier.background(color = Color.White)) {
            AppRowTextField(
                leadingIcon = {
                    AppIconButton(icon = R.drawable.ic_menu)
                },
                placeholder = {
                    Text("placeholder")
                },
                trailingIcon = {
                    AppIconButton(icon = R.drawable.ic_settings)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTaskTextFieldPreview() {
    AppTheme {
        Box(modifier = Modifier.background(color = Color.White)) {
            AppTaskTextField(placeholderText = "Placeholder")
        }
    }
}
