package com.example.hqmarvel.repository

import android.content.Context
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.datasource.HQApiClientDataSource
import com.example.hqmarvel.datasource.HQDataBaseDataSource

class HQRepository(context: Context) {
    private val hqApiClientDataSource = HQApiClientDataSource()
    private val hqDataBaseDataSource = HQDataBaseDataSource(context)
    suspend fun getHQData(): Result<List<ComicData>?> {
        return try {
            val result = hqApiClientDataSource.getHQData()

            if (result.isSuccess) {
                persistData(result.getOrNull())
                result
            } else {
                getLocalData()
            }
        } catch (e: Exception) {
            getLocalData()
        }
    }

    private suspend fun getLocalData() = hqDataBaseDataSource.getHQData()

    private suspend fun persistData(result: List<ComicData>?){
        hqDataBaseDataSource.clearData()
        result?.let {
            hqDataBaseDataSource.saveData(it)
        }
    }
}