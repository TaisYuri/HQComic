package com.example.hqmarvel.repository

import android.util.Log
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.datasource.AgentApiClientDataSource

class AgentRepository {

    private val agentApiClientDataSource = AgentApiClientDataSource()

    suspend fun getAgentData(): Result<List<AgentData>?> {
        return try {
            val result = agentApiClientDataSource.getAgentData()

            if (result.isSuccess) {
                Log.d("DATA REPOSITORY", result.isSuccess.toString())
                result
            } else {
                Log.d("DATA REPOSITORY ELSE", result.isSuccess.toString())
                getLocalData()
            }
        } catch (e: Exception) {
            Log.d("DATA REPOSITORY CA", e.message.toString())
            getLocalData()
        }
    }

    private fun getLocalData() = Result.success(emptyList<AgentData>())

}