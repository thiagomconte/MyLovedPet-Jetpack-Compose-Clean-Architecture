package com.conte.data.module.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet_event")
data class PetEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "pet_id")
    val petId: Int,
    @ColumnInfo(name = "notification_id")
    val notificationId: Int,
)
