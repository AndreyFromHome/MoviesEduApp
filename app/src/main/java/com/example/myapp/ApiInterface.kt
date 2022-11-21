package com.example.myapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/characters?limit=10&offset=0")
    fun getMovies() : retrofit2.Call<List<MoviesItem>>

    companion object {

        var BASE_URL = "https://breakingbadapi.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}