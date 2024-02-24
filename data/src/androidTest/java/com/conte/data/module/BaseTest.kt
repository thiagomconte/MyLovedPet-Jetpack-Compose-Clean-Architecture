package com.conte.data.module

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.conte.data.module.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
open class BaseTest : AndroidJUnitRunner() {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Inject
    lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}