package com.example.testapplicationmovie.model.retrofit

import com.example.testapplicationmovie.model.CompanionMovie
import com.example.testapplicationmovie.utilites.QUERY_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FirstRetrofitServices {

    @GET("all.json")
    fun getMovie(
        @Query(QUERY_API_KEY) apiKey: String,
    ): Call<CompanionMovie>

}