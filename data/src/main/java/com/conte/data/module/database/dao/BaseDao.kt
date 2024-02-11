package com.conte.data.module.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Upsert

interface BaseDao<T> {

    /**
     * Insert an entity in the database.
     *
     * @param entity the row to be inserted.
     *
     * @return the row index
     *
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: T): Long

    /**
     * Insert an array of entities in the database.
     *
     * @param entities the row to be inserted.
     *
     * @return the row index
     *
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<T>): List<Long>

    /**
     * Update an entity from the database.
     *
     * @param entity the row to be updated
     *
     */
    @Update
    suspend fun update(entity: T)

    /**
     * Update an array of entities from the database.
     *
     * @param entities the rows to be updated
     *
     */
    @Update
    suspend fun update(entities: List<T>)

    /**
     * Delete an entity from the database.
     *
     * @param entity the row to be deleted
     *
     */
    @Delete
    suspend fun delete(entity: T)

    @Upsert
    suspend fun insertOrUpdate(entity: T)

    @Upsert
    suspend fun insertOrUpdate(entities: List<T>)

    companion object {
        const val ROW_NOT_INSERTED = -1L
    }
}