package com.example.hqmarvel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hqmarvel.data.dao.ComicDao
import com.example.hqmarvel.data.dao.ImageDao
import com.example.hqmarvel.data.dao.TextObjectDao
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.data.model.response.ImageData
import com.example.hqmarvel.data.model.response.TextObjects

@Database(
    entities = [ImageData::class, TextObjects::class, ComicData::class],
    version = 1,
    exportSchema = false
)
abstract  class ComicsDataBase: RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun textObjectDao(): TextObjectDao
    abstract fun comicDao(comicsDatabase: ComicsDataBase): ComicDao


    companion object{
        @Volatile
        private var instance: ComicsDataBase? = null

        fun getDataBase(context: Context): ComicsDataBase{
            return instance ?: synchronized(this){
                val dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    ComicsDataBase::class.java,
                    "comic_data_base"
                ).build()
                this.instance = dataBase
                return dataBase
            }
        }
    }




}