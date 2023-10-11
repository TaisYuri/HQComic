package com.example.hqmarvel.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.hqmarvel.data.ComicWithAllProperties
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.database.ComicsDataBase

@Dao
abstract class ComicDao(
    comicsDataBase: ComicsDataBase
): BaseDao<ComicData> {

    private val imageDao = comicsDataBase.imageDao()
    private val textObjectDao = comicsDataBase.textObjectDao()

    @Transaction
    open suspend fun insertComicList(comicList: List<ComicData>){
        comicList.forEach {
            insertComic(it)
        }
    }

    @Transaction  //garante que ou tudo será salvo ou nada será salvo.
    open suspend fun insertComic(comic: ComicData){
        comic.thumbnail?.comicId = comic.id

        comic.images?.forEach {
            it.comicId = comic.id
        }

        comic.textObjects?.forEach {
            it.comicId = comic.id
        }

        insert(comic)
        comic.textObjects?.let {
            textObjectDao.insertList(it)
        }

        comic.images?.let {
            imageDao.insertList(it)
        }
    }

    @Transaction
    @Query("DELETE FROM comicdata")
    abstract suspend fun clearComicData()

    @Transaction
    @Query("SELECT * FROM comicdata")
    abstract suspend fun getAllComics(): List<ComicWithAllProperties>?

    @Transaction
    @Query("SELECT * FROM comicdata WHERE id=:id")
    abstract suspend fun getComic(id: Int): ComicWithAllProperties?

}