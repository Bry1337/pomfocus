package io.bry1337.pomfocus.android.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Bryan on 3/2/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalMaterialApi::class)
interface BottomSheetDescriptor {
    val launchPresentation: ModalBottomSheetValue
        get() = ModalBottomSheetValue.HalfExpanded

    val disallowedPresentations: Set<ModalBottomSheetValue>
        get() = emptySet()
}

@OptIn(ExperimentalMaterialApi::class)
data class BottomSheetOperation<T : BottomSheetDescriptor>(
    val sheetDescriptor: T,
    val sheetState: ModalBottomSheetState,
    val togglePresentation: () -> Unit,
    val presentSheet: (T) -> Unit
) {
    val isVisible: Boolean
        get() = sheetState.isVisible

    suspend fun hide() = sheetState.hide()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : BottomSheetDescriptor> rememberBottomSheetOperation(
    scope: CoroutineScope,
    initialBottomSheetDescriptor: T
): BottomSheetOperation<T> {
    var bottomSheetDescriptor by remember { mutableStateOf(initialBottomSheetDescriptor) }
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            !bottomSheetDescriptor.disallowedPresentations.contains(it)
        }
    )

    val toggleBottomSheet: () -> Unit =
        remember {
            {
                scope.launch {
                    if (bottomSheetState.isVisible) {
                        bottomSheetState.hide()
                    } else {
                        bottomSheetState.show()
                    }
                }
            }
        }

    val presentBottomSheet: (T) -> Unit = remember {
        {
            scope.launch {
                if (bottomSheetState.isVisible) {
                    // Hide existing content
                    bottomSheetState.hide()
                }
                // then present the expected content
                bottomSheetDescriptor = it
                toggleBottomSheet()
            }
        }
    }

    return remember(
        bottomSheetDescriptor,
        bottomSheetState,
        toggleBottomSheet,
        presentBottomSheet
    ) {
        BottomSheetOperation(
            sheetDescriptor = bottomSheetDescriptor,
            sheetState = bottomSheetState,
            togglePresentation = toggleBottomSheet,
            presentSheet = presentBottomSheet
        )
    }
}
