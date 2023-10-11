package com.example.hqmarvel.data.api

import com.example.hqmarvel.data.model.response.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("v1/public/comics")
    suspend fun getComicList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): Response<ComicResponse>
}