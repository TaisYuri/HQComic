package com.example.hqmarvel.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hqmarvel.data.model.response.ImageData
import com.example.hqmarvel.data.model.response.TextObjects

@Dao
interface TextObjectDao: BaseDao<TextObjects> {

    @Query("SELECT * FROM textObjects")
    suspend fun getAllTexts(): List<TextObjects>?

    @Query("SELECT * FROM textObjects WHERE textObjectId=:textObjectId")
    suspend fun getText(textObjectId: Int): TextObjects?

}