package com.example.testapplicationmovie.model

import android.util.Log
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

    val listOfMovie = MutableLiveData<MutableList<Movie>>()

    fun initRetrofit() {
        val mService = firstRetrofitService
        mService.getMovie(API_KEY)
            .enqueue(object : Callback<CompanionMovie> {
                override fun onResponse(
                    call: Call<CompanionMovie>,
                    response: Response<CompanionMovie>
                ) {
                    val listOfAll = response.body()?.results as MutableList<Movie>

                    Log.i("result::", listOfAll.toString())
                    listOfMovie.value = listOfAll
                }

                override fun onFailure(call: Call<CompanionMovie>, t: Throwable) {
                    Log.e("initGetPhotoRetrofit:", t.toString())
                }
            })
    }

}