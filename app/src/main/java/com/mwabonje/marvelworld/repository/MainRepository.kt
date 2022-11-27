package com.mwabonje.marvelworld.repository

import androidx.lifecycle.liveData
import com.mwabonje.marvelworld.database.MarvelDao
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.network.MarvelApi
import com.mwabonje.marvelworld.network.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: MarvelApi, private val dao: MarvelDao,
) {

    fun getCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null)) //for the loading stuff
        try {
            //Pick data from local db
            val data = dao.getMarvelCharacters()
            if (data.isNotEmpty()){
                emit(Resource.success(data = data))
            }

            //make network call
            val response = api.sendRequest()
            if (response.isSuccessful) {
                val dataRes = response.body()?.data?.marvelCharacters
                dataRes?.let { networkList ->
                    val list = mutableListOf<MarvelEntity>()
                    networkList.forEach {
                        list.add(
                            MarvelEntity(it.characterId, it.characterName, it.characterDescription))
                    }
                    //
                    dao.insertAll(list)
                    emit(Resource.success(data = dao.getMarvelCharacters()))
                    //
                }
            }else{
                emit(Resource.error(data = null, message = response.message()))
            }
        } catch (e: Exception) {
            emit(
                Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getCharacterDetails(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null)) //for the loading stuff
        try {
            //make network call
            val response = api.characterDetailsRequest(id)
            if (response.isSuccessful) {
                val dataRes = response.body()?.data?.marvelCharacters?.get(0)
                dataRes?.let {
                    emit(Resource.success(data = it))
                }
            }else{
                emit(Resource.error(data = null, message = response.message()))
            }
        } catch (e: Exception) {
            emit(
                Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}