package com.example.myapp.model.repository

import com.example.myapp.data.MoviesItem
import com.example.myapp.data.MoviesItemDetails
import retrofit2.Call

interface MoviesDBRepository {

    /**
     * Returns list of characters Breaking Bad [MoviesItem]
     * */
    fun getMovies() : Call<List<MoviesItem>>

    /**
     * Returns information for a single character by returning [MoviesItemDetails]
     * @param char_id - identification number of the needed character
     * */
    fun getMoviesDetails(char_id: Int): Call<List<MoviesItemDetails>>
}