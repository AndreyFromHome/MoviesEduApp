package com.example.myapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/characters?limit=10&offset=0")
    fun getMovies() : retrofit2.Call<List<MoviesItem>>

    @GET("api/characters/{char_id}")
    fun getMovieDetails(@Path("char_id") char_id : Int) : retrofit2.Call<List<MoviesItemDetails>>

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