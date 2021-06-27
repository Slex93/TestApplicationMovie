package com.example.testapplicationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplicationmovie.model.Movie
import com.example.testapplicationmovie.model.RetrofitRepository

class MainViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val listOfMovies: MutableLiveData<Movie> = repository.listOfMovie

    fun initRetrofit(offset: Int) {
        repository.initRetrofit(offset)
    }

}