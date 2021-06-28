package com.example.testapplicationmovie.model

import android.database.Observable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.testapplicationmovie.model.retrofit.FirstRetrofitClient
import com.example.testapplicationmovie.model.retrofit.FirstRetrofitServices
import com.example.testapplicationmovie.utilites.API_KEY
import com.example.testapplicationmovie.utilites.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {

    private val firstRetrofitService: FirstRetrofitServices
        get() = FirstRetrofitClient.getClient(BASE_URL)
            .create(FirstRetrofitServices::class.java)

    val listOfMovie = MutableLiveData<Movie>()

    val error = MutableLiveData<String>()

    fun initRetrofit(offset: Int) {
        val mService = firstRetrofitService
        mService.getMovie(offset, API_KEY)
            .enqueue(object : Callback<CompanionMovie> {
                override fun onResponse(
                    call: Call<CompanionMovie>,
                    response: Response<CompanionMovie>
                ) {
                    if (response.isSuccessful){
                        val listOfAll = response.body()?.results as MutableList<Movie>
                        listOfAll.forEach {
                            listOfMovie.value = it
                        }
                    } else {
                        error.value = response.message()
                    }

                }

                override fun onFailure(call: Call<CompanionMovie>, t: Throwable) {
                }
            })
    }

}