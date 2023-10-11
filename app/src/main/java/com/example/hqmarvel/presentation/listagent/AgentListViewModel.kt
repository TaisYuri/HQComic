package com.example.hqmarvel.presentation.listagent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hqmarvel.RetrofitBuilder
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.ApiCredentials
import com.example.hqmarvel.data.DataState
import com.example.hqmarvel.helper.ApiHelper
import kotlinx.coroutines.launch

class AgentListViewModel : ViewModel() {
    var listAgentLiveData = MutableLiveData<List<AgentData>?>()
    var appState = MutableLiveData<DataState>()

    init {
        appState.postValue(DataState.Loading)
        getListAgent()
    }

    private fun getListAgent() {
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privateKey}${ApiCredentials.publickKey}"
        val hash = ApiHelper.generateMD5Hash(input)

        //CRIANDO COROUTINE
        viewModelScope.launch {
           // val response = serviceAgent.getAgentList(timestamp, ApiCredentials.publickKey, hash, 50)
            val response = RetrofitBuilder.apiService.getAgentList(timestamp, ApiCredentials.publickKey, hash, 50)


            if (response.isSuccessful) {
                listAgentLiveData.postValue(response.body()?.data?.results)
                appState.postValue(DataState.Success)
            } else {
                appState.postValue(DataState.Error)
            }

        }
    }
}