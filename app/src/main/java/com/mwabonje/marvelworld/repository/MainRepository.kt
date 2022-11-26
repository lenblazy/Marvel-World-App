package com.mwabonje.marvelworld.repository

import androidx.lifecycle.liveData
import com.mwabonje.marvelworld.database.MarvelDao
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.models.DefaultResponse
import com.mwabonje.marvelworld.network.MarvelApi
import com.mwabonje.marvelworld.network.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: MarvelApi, private val dao: MarvelDao,
) {

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null)) //for the loading stuff
        try {
            //Pick data from local db
//            val data = dao.getMarvelCharacters().value
//            if (data != null && data.isNotEmpty()){
//                emit(Resource.success(data = data))
//            }

            //make network call
            val response = api.sendRequest()
            if (response.isSuccessful) {
                //TODO REMOVE LATER
                response.body()?.data?.marvelCharacters?.let { networkList ->
                    val list = mutableListOf<MarvelEntity>()
                    networkList.forEach {
                        list.add(
                            MarvelEntity(it.characterId, it.characterName, it.characterDescription)
                        )
                    }
                    emit(Resource.success(data = list))
                }
//                saveResult(response.body())
            }else{
                emit(Resource.error(data = null, message = response.message()))
            }
        } catch (e: Exception) {
            emit(
                Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    /**
     * Saves network result to db
     */
    private fun saveResult(response: DefaultResponse?) = liveData(Dispatchers.IO){
        response?.data?.marvelCharacters?.let { networkList ->
            val list = mutableListOf<MarvelEntity>()
            networkList.forEach {
                list.add(
                    MarvelEntity(it.characterId, it.characterName, it.characterDescription)
                )
            }

            dao.insertAll(list)
            //Notify of new data
            emit(Resource.success(data = dao.getMarvelCharacters()))
        }
    }

}