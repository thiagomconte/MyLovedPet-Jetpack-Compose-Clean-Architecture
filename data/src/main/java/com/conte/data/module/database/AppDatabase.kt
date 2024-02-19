package com.conte.data.module.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.conte.data.module.database.converter.PetConverter
import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.database.entity.PetEntity
import com.conte.data.module.database.entity.PetEventEntity

@Database(
    version = 1,
    entities = [PetEntity::class, PetEventEntity::class]
)
@TypeConverters(PetConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun eventDao(): PetEventDao
}