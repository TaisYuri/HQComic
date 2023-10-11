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
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQViewModel(application: Application): AndroidViewModel(application) {

    var detailsLiveData = MutableLiveData<ComicData>()

    val listLiveData: LiveData<List<ComicData>?>
        get() = _listLiveData
    private val _listLiveData = MutableLiveData<List<ComicData>?>()

    //livedata para navegação entre as telas
    val navigationToDetailLiveData: LiveData<Event<Unit>>
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Event<Unit>>()

    val appState: LiveData<DataState>
        get() = _appState
    private val _appState = MutableLiveData<DataState>()

    //Instancia o banco de dados
    private val comicDatabase = ComicsDataBase.getDataBase(application)
    private val comicDao = comicDatabase.comicDao(comicDatabase)

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicService = retrofit.create(Service::class.java)

    init{
        _appState.postValue(DataState.Loading)
        getComicData()
    }

    private fun getComicData(){
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privateKey}${ApiCredentials.publickKey}"
        val hash = ApiHelper.generateMD5Hash(input)

        //CRIANDO COROUTINE
        viewModelScope.launch {
            try{
                val response = comicService.getComicList(timestamp, ApiCredentials.publickKey,hash, 50)

                if(response.isSuccessful){
                    val comics = response.body()?.data?.results

                    comics?.let {
                        persistComicData(it)
                    }

                    _listLiveData.postValue(comics)
                    _appState.postValue(DataState.Success)
                }else{
                    errorHandling()
                }
            }catch (e: Exception){
                errorHandling()
            }

        }
    }

    // FUNÇÕES DE BANCO DE DADOS
    private suspend fun persistComicData(comicList: List<ComicData>){
        comicDao.clearComicData()  //LIMPA TODOS OS DADOS DO BANCO DE DADOS
        comicDao.insertComicList(comicList)
    }

    private suspend fun loadPersistedComicData() = comicDao.getAllComics()?.map {
        mapComicWithProprietiesToComic(it)
    }

    private fun mapComicWithProprietiesToComic(comicWithAllProperties: ComicWithAllProperties): ComicData{
        comicWithAllProperties.comic.images = comicWithAllProperties.images
        comicWithAllProperties.comic.textObjects = comicWithAllProperties.textObjects
        return comicWithAllProperties.comic
    }

    private suspend fun errorHandling(){
        val comicList = loadPersistedComicData()

        if(comicList.isNullOrEmpty()){
            _appState.postValue(DataState.Error)
        }else{
            _listLiveData.postValue(comicList)
            _appState.postValue(DataState.Success)
        }
    }


    fun onSelected(position: Int){
        val hqDetails = _listLiveData?.value?.get(position)
        hqDetails.let {
            detailsLiveData.postValue(it)
            _navigationToDetailLiveData.postValue(Event(Unit))
        }
    }
}