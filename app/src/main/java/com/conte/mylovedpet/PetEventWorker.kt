package com.conte.mylovedpet

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class PetEventWorker @Inject constructor(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val notificationName = params.inputData.getString(KEY_NOTIFICATION_NAME)
            val notificationDescription = params.inputData.getString(KEY_NOTIFICATION_DESCRIPTION)
            if (notificationName != null && notificationDescription != null) {
                createNotificationChannel()
                val notificationId = showNotification(notificationName, notificationDescription)
                val outputData = Data.Builder()
                    .putInt(KEY_NOTIFICATION_ID, notificationId)
                    .build()
                Result.success(outputData)
            } else {
                Result.failure()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(notificationName: String, notificationDescription: String): Int {
        val notificationId = Random.nextInt()
        val notification = NotificationCompat.Builder(applicationContext, APP_CHANNEL_ID)
            .setContentTitle(notificationName)
            .setContentText(notificationDescription)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(notificationId, notification)
        return notificationId
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(APP_CHANNEL_ID, APP_CHANNEL_NAME, importance)
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(appContext, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val APP_CHANNEL_ID = "my_loved_pet_channel"
        const val APP_CHANNEL_NAME = "event_notifications"
        const val KEY_NOTIFICATION_NAME = "KEY_NOTIFICATION_NAME"
        const val KEY_NOTIFICATION_DESCRIPTION = "KEY_NOTIFICATION_DESCRIPTION"
        const val KEY_NOTIFICATION_ID = "KEY_NOTIFICATION_ID"
    }
}