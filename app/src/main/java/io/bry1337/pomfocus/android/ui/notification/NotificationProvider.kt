package io.bry1337.pomfocus.android.ui.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.theme.AppContext

/**
 * Created by Bryan on 3/9/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

object NotificationProvider {

    private const val NOTIFICATION_ID = 1001
    private const val NOTIFICATION_CHANNEL_ID = "POMFOCUS_APP"
    private const val NOTIFICATION_CHANNEL_NAME = "POMFOCUS_CHANNEL"
    private val appContext = AppContext.getContext() as Context

    private fun createNotificationChannel(descriptionText: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel =
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                importance
            ).apply {
                description = descriptionText
            }
        val notificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(
        @StringRes contentTitle: Int,
        @StringRes contentText: Int,
        channelDescription: String = ""
    ) {
        createNotificationChannel(descriptionText = channelDescription)
        val notificationBuilder = NotificationCompat.Builder(appContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_menu)
            .setContentTitle(appContext.getString(contentTitle))
            .setContentText(appContext.getString(contentText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(appContext)) {
                notify(NOTIFICATION_ID, notificationBuilder.build())
            }
        }
    }
}
