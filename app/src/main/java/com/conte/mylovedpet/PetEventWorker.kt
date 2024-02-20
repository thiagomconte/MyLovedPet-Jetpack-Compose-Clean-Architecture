package com.conte.mylovedpet

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PetEventWorker @Inject constructor(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val notificationName = params.inputData.getString(KEY_NOTIFICATION_NAME)
            val notificationDescription = params.inputData.getString(KEY_NOTIFICATION_DESCRIPTION)
            if (notificationName != null && notificationDescription != null) {
                showNotification(notificationName, notificationDescription)
                Result.success()
            } else {
                Result.failure()
            }
        }
    }

    private fun showNotification(notificationName: String, notificationDescription: String) {
        // Configurar e exibir a notificação aqui
        val notification = NotificationCompat.Builder(applicationContext, "channelId")
            .setContentTitle("Título da Notificação")
            .setContentText("Conteúdo da Notificação")
            .build()

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(1, notification)
    }

    companion object {
        const val KEY_DATE = "KEY_DATE"
        const val KEY_NOTIFICATION_NAME = "KEY_NOTIFICATION_NAME"
        const val KEY_NOTIFICATION_DESCRIPTION = "KEY_NOTIFICATION_DESCRIPTION"
    }
}