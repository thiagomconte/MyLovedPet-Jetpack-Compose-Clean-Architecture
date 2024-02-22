package com.conte.mylovedpet.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.conte.domain.module.commons.logInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.random.Random

class NotificationHelper @Inject constructor(
    @ApplicationContext val context: Context,
) {

    private val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
    fun generateNotificationRandomId() = Random.nextInt()

    fun createNotificationAndNotify(notificationId: Int, notificationName: String, notificationDescription: String) {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(context, APP_CHANNEL_ID)
            .setContentTitle(notificationName)
            .setContentText(notificationDescription)
            .setSmallIcon(com.conte.design_system.R.drawable.dog_icon)
            .build()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(notificationId, notification)
            logInfo { "Notification successfully notified!" }
        }
    }


    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(APP_CHANNEL_ID, APP_CHANNEL_NAME, importance)
        val notificationManager: NotificationManager = getSystemService(context, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        logInfo { "Notification Channel created successfully!" }
    }

    companion object {
        const val APP_CHANNEL_ID = "my_loved_pet_channel"
        const val APP_CHANNEL_NAME = "pet_event_notifications"
    }
}