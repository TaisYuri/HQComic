package com.example.hqmarvel.datasource

import android.util.Log
import com.example.hqmarvel.data.ApiCredentials
import com.example.hqmarvel.data.api.AgentService
import com.example.hqmarvel.data.api.Service
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.helper.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AgentApiClientDataSource : AgentDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service = retrofit.create(AgentService::class.java)
    override suspend fun getAgentData(): Result<List<AgentData>?> =
        withContext(Dispatchers.IO) {
            val timestamp = ApiHelper.getCurrentTimeStamp()
            val input = "$timestamp${ApiCredentials.privateKey}${ApiCredentials.publickKey}"
            val hash = ApiHelper.generateMD5Hash(input)

            val response = service.getAgentList(timestamp, ApiCredentials.publickKey, hash, 50)

            // when{
            //   response.isSuccessful -> Result.success(response.body()?.data?.results)
            //  else -> Result.failure(Throwable(response.message()))
            //}

            if (response.isSuccessful) {
                Log.d("DATA SOURCE", response.isSuccessful.toString())
                Result.success(response.body()?.data?.results)
            } else {
                Log.d("DATA SOURCE ELSE", response.isSuccessful.toString())
                Result.failure(Throwable(response.message()))
            }
        }

}