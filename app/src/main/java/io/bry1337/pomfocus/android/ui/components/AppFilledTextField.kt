package io.bry1337.pomfocus.android.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import io.bry1337.pomfocus.android.ui.theme.AppTheme
import io.bry1337.pomfocus.android.utils.AppSizing

/**
 * Created by Bryan on 3/17/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun AppFilledTextField(
    modifier: Modifier = Modifier,
    sized: AppSizing = AppSizing.md,
    placeholder: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(size = sized.roundedCornersScaling),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        cursorColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
) {
    var textValue by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = {
        textValue = it
    }
    TextField(
        value = textValue, onValueChange = onValueChange, modifier = modifier,
        placeholder = placeholder, label = label,
        trailingIcon = trailingIcon,
        isError = isError,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape
    )
}

@Preview(showBackground = true)
@Composable
fun AppFilledTextFieldPreview() {
    AppTheme {
        AppFilledTextField()
    }
}
