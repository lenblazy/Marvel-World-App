package com.mwabonje.marvelworld.viewmodel

import androidx.lifecycle.ViewModel
import com.mwabonje.marvelworld.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    val marvelCharacters get() = repository.getMovies()

}