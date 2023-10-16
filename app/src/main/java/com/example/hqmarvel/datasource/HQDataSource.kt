package com.example.hqmarvel.datasource

import android.content.Context
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.model.response.ComicData

interface HQDataSource {
    suspend fun getHQData(): Result<List<ComicData>?>
    suspend fun saveData(comicList: List<ComicData>)

    suspend fun clearData()
}