package com.conte.mylovedpet.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.conte.mylovedpet.utils.NotificationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContextApplication(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideNotificationHelper(@ApplicationContext context: Context) = NotificationHelper(context)

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager = WorkManager.getInstance(context)
}