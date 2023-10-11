package com.example.hqmarvel.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.hqmarvel.data.model.response.ImageData


interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Insert
    suspend fun insertList(entities: List<T>)

    @Update
    suspend fun updateList(entities: List<T>)

    @Delete
    suspend fun deleteList(entities: List<T>)
}