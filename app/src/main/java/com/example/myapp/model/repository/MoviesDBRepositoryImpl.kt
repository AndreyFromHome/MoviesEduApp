package com.example.myapp.model.repository

import com.example.myapp.data.MoviesItem
import com.example.myapp.data.MoviesItemDetails
import com.example.myapp.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {
    private val apiInterface = ApiInterface.create()


    // Делать запрос в API
    // И возвращать результат с героями Breaking Bad
    override fun getMovies(): Call<List<MoviesItem>> {
        return apiInterface.getMovies()
    }

    // Делать запрос в API
    // И возвращать результат с детальным описанием героев Breaking Bad
    override fun getMoviesDetails(char_id: Int): Call<List<MoviesItemDetails>> {
        return apiInterface.getMovieDetails(char_id)
    }
}