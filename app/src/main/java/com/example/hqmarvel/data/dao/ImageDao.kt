package com.example.hqmarvel.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hqmarvel.data.model.response.ImageData

@Dao
interface ImageDao: BaseDao<ImageData> {

    @Query("SELECT * FROM imagedata")
    suspend fun getAllImages(): List<ImageData>?

    @Query("SELECT * FROM imagedata WHERE imageId=:imageId")
    suspend fun getImage(imageId: Int): ImageData?

}