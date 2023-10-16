package com.example.hqmarvel.datasource

import com.example.hqmarvel.data.ApiCredentials
import com.example.hqmarvel.data.api.AgentService
import com.example.hqmarvel.data.api.Service
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.helper.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQApiClientDataSource: HQDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicService = retrofit.create(Service::class.java)

    private val timestamp = ApiHelper.getCurrentTimeStamp()
    private val input = "$timestamp${ApiCredentials.privateKey}${ApiCredentials.publickKey}"
    private val hash = ApiHelper.generateMD5Hash(input)
    override suspend fun getHQData(): Result<List<ComicData>?> =
       withContext(Dispatchers.IO){
           val response = comicService.getComicList(timestamp, ApiCredentials.publickKey,hash, 50)

           when{
               response.isSuccessful -> Result.success(response.body()?.data?.results)
               else -> Result.failure(Throwable(response.message()))
           }
       }

    override suspend fun saveData(comicList: List<ComicData>) {
        //NO-OP
    }

    override suspend fun clearData() {
        //NO-OP
        //no operation (quando não existe implementação)
    }

}