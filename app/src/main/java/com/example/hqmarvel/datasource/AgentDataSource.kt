package com.example.hqmarvel.datasource

import com.example.hqmarvel.data.model.response.AgentData

interface AgentDataSource {
    suspend fun getAgentData(): Result<List<AgentData>?>
}