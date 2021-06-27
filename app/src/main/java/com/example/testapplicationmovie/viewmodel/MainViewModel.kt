package com.example.testapplicationmovie.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testapplicationmovie.model.RetrofitRepository

class MainViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val listOfMovies = repository.listOfMovie

    fun initRetrofit() {
        repository.initRetrofit()
    }

}