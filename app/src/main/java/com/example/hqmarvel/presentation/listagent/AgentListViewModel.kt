package com.example.hqmarvel.presentation.listagent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.DataState
import com.example.hqmarvel.repository.AgentRepository
import kotlinx.coroutines.launch

class AgentListViewModel : ViewModel() {
    var listAgentLiveData = MutableLiveData<List<AgentData>?>()
    var appState = MutableLiveData<DataState>()

    private val agentRepository = AgentRepository()

    init {
        appState.postValue(DataState.Loading)
        getListAgent()
    }

    private fun getListAgent() {
        appState.postValue(DataState.Loading)

        viewModelScope.launch {
            val agentListResult = agentRepository.getAgentData()

            agentListResult.fold(
                onSuccess = {
                    listAgentLiveData.postValue(it)
                    appState.postValue(DataState.Success)
                },
                onFailure = {
                    appState.postValue(DataState.Error)
                }
            )

        }
    }
}