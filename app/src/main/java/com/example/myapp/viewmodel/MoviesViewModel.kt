package com.example.myapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapp.data.MoviesItemDetails
import com.example.myapp.model.repository.MoviesDBRepository
import com.example.myapp.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _moviesDetails = MutableLiveData<MoviesItemDetails>
    val movieDetails: LiveData<MoviesItemDetails?> = _movieDetails

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("MyLog", "OnResponse Success ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }
        })
    }

    fun getMovieDetails(char_id: Int) {
        val response = mMoviesRepository.getMovieDetails(char_id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("MyLog", "OnResponse Success ${call.toString()}")
                _movieDetails.postValue(response?.body())
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("MyTag", "onFailure: ${t?.message}")
            }
        })
    }


}