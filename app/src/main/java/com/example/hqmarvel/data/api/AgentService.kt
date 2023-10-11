package com.example.hqmarvel.data.api

import com.example.hqmarvel.data.model.response.AgentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AgentService {

    @GET("v1/public/characters")
    suspend fun getAgentList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): Response<AgentResponse>
}