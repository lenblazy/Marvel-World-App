package com.mwabonje.marvelworld.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mwabonje.marvelworld.models.MarvelCharacter
import com.mwabonje.marvelworld.network.Resource
import com.mwabonje.marvelworld.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    val marvelCharacters get() = repository.getCharacters()

    fun characterDetails(id: String): LiveData<Resource<MarvelCharacter>> {
        return repository.getCharacterDetails(id = id)
    }

}