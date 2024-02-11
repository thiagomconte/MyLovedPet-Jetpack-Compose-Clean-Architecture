package com.conte.data.module.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.conte.data.module.database.converter.PetConverter
import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.entity.PetEntity

@Database(entities = [PetEntity::class], version = 1)
@TypeConverters(PetConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}