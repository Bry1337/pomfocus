package io.bry1337.pomfocus.android.ui.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.extensions.themePaddingAll
import io.bry1337.pomfocus.android.utils.AppSizing
import io.bry1337.pomfocus.android.utils.TimerConstants
import io.bry1337.pomfocus.theme.DimensionConstants

/**
 * Created by Bryan on 9/20/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NotificationPermissionRequest(
    notificationPermissionState: PermissionState,
    modifier: Modifier = Modifier
) {
    var retry by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val screenSize = LocalConfiguration.current
    val alertDialogHeight = screenSize.screenHeightDp / 3
    val textToShow = if (notificationPermissionState.status.shouldShowRationale) {
        // If the user has denied the permission but the rationale can be shown,
        // then gently explain why the app requires this permission
        stringResource(id = R.string.request_permission_notification_1)
    } else {
        // If it's the first time the user lands on this feature, or the user
        // doesn't want to be asked again for this permission, explain that the
        // permission is required
        stringResource(id = R.string.request_permission_notification_2)
    }
    AlertDialog(onDismissRequest = { /* TODO we don't want to implement
     dismiss request since this is a required dialog */
    }) {
        Surface(
            modifier = modifier.height(alertDialogHeight.dp),
            shape = RoundedCornerShape(DimensionConstants.roundedCornersMd),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier.themePaddingAll(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(textToShow, style = MaterialTheme.typography.titleMedium)
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .themePaddingAll(AppSizing.xs),
                    onClick = {
                        if (notificationPermissionState.status.shouldShowRationale) {
                            notificationPermissionState.launchPermissionRequest()
                            retry++
                        } else if (!notificationPermissionState.status.isGranted &&
                            !notificationPermissionState.status.shouldShowRationale &&
                            retry == TimerConstants.NOTIFICATION_PERMISSION_TRIES
                        ) {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            val uri: Uri =
                                Uri.fromParts(TimerConstants.SCHEME, context.packageName, null)
                            intent.data = uri
                            context.startActivity(intent)
                        } else {
                            notificationPermissionState.launchPermissionRequest()
                            retry++
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.request_permission_button_1))
                }
            }
        }
    }
}
