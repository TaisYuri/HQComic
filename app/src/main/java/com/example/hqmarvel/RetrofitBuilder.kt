package com.example.hqmarvel

import com.example.hqmarvel.data.ApiCredentials
import com.example.hqmarvel.data.api.AgentService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RetrofitBuilder {

    companion object{
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if(!::INSTANCE.isInitialized){
                INSTANCE = Retrofit.Builder()
                    .baseUrl(ApiCredentials.baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        val apiService = getRetrofitInstance().create(AgentService::class.java)
    }
}

