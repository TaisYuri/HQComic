package com.example.hqmarvel.datasource

import android.content.Context
import com.example.hqmarvel.data.ComicWithAllProperties
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.database.ComicsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HQDataBaseDataSource(context: Context) : HQDataSource {

    //Instancia o banco de dados
    private val comicDatabase = ComicsDataBase.getDataBase(context)
    private val comicDao = comicDatabase.comicDao(comicDatabase)

    override suspend fun getHQData(): Result<List<ComicData>?> =
        withContext(Dispatchers.IO) {
            Result.success(loadPersistedComicData())
        }

    override suspend fun saveData(comicList: List<ComicData>) {
        comicDao.insertComicList(comicList)
    }

    override suspend fun clearData() {
        comicDao.clearComicData()  //LIMPA TODOS OS DADOS DO BANCO DE DADOS
    }

    private suspend fun loadPersistedComicData() = comicDao.getAllComics()?.map {
        mapComicWithProprietiesToComic(it)
    }

    private fun mapComicWithProprietiesToComic(comicWithAllProperties: ComicWithAllProperties): ComicData {
        comicWithAllProperties.comic.images = comicWithAllProperties.images
        comicWithAllProperties.comic.textObjects = comicWithAllProperties.textObjects
        return comicWithAllProperties.comic
    }
}