package com.example.hqmarvel.presentation.listhq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hqmarvel.data.DataState
import com.example.hqmarvel.data.api.Service
import com.example.hqmarvel.data.ApiCredentials
import com.example.hqmarvel.data.ComicWithAllProperties
import com.example.hqmarvel.data.Event
import com.example.hqmarvel.helper.ApiHelper
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.database.ComicsDataBase
import com.example.hqmarvel.repository.HQRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQViewModel(application: Application): AndroidViewModel(application) {

    var detailsLiveData = MutableLiveData<ComicData>()
    val listLiveData = MutableLiveData<List<ComicData>?>()
    val appState: LiveData<DataState>
        get() = _appState
    private val _appState = MutableLiveData<DataState>()

    //livedata para navegação entre as telas
    val navigationToDetailLiveData: LiveData<Event<Unit>>
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Event<Unit>>()

    private val hqRepository = HQRepository(application)

    init{
        _appState.postValue(DataState.Loading)
        getComicData()
    }

    fun onSelected(position: Int){
        val hqDetails = listLiveData?.value?.get(position)
        hqDetails.let {
            detailsLiveData.postValue(it)
            _navigationToDetailLiveData.postValue(Event(Unit))
        }
    }
    private fun getComicData(){
        _appState.postValue(DataState.Loading)

        viewModelScope.launch {
            val comicListResult = hqRepository.getHQData()

            comicListResult.fold(
                onSuccess = {
                    listLiveData.postValue(it)
                    _appState.postValue(DataState.Success)
                },
                onFailure = {
                    _appState.postValue(DataState.Error)
                }
            )

        }
    }
}