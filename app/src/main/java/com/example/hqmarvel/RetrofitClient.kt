package com.example.hqmarvel

import com.example.hqmarvel.data.ApiCredentials
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    companion object{
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitClientInstance(): Retrofit{
            if(!::INSTANCE.isInitialized){
                INSTANCE = Retrofit.Builder()
                    .baseUrl(ApiCredentials.baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }

            return INSTANCE
        }

        fun <S> createService(endpoint: Class<S>): S{
            return getRetrofitClientInstance().create(endpoint)
        }
    }
}