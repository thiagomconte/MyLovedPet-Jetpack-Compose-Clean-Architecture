package com.conte.mylovedpet

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.conte.mylovedpet.utils.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class PetEventWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val notificationName = params.inputData.getString(KEY_NOTIFICATION_NAME)
            val notificationDescription = params.inputData.getString(KEY_NOTIFICATION_DESCRIPTION)
            if (notificationName != null && notificationDescription != null) {
                notificationHelper.createNotificationAndNotify(
                    notificationId = notificationHelper.generateNotificationRandomId(),
                    notificationName = notificationName,
                    notificationDescription = notificationDescription
                )
                Result.success()
            } else {
                Result.failure()
            }
        }
    }

    companion object {
        const val KEY_NOTIFICATION_NAME = "KEY_NOTIFICATION_NAME"
        const val KEY_NOTIFICATION_DESCRIPTION = "KEY_NOTIFICATION_DESCRIPTION"
    }
}