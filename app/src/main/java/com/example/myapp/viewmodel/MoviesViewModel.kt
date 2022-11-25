package com.example.myapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapp.data.MoviesItem
import com.example.myapp.data.MoviesItemDetails
import com.example.myapp.model.repository.MoviesDBRepository
import com.example.myapp.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<MoviesItem?>>()
    val movies: LiveData<List<MoviesItem?>> = _movies

    private val _moviesDetails = MutableLiveData<MoviesItemDetails>()
    val moviesDetails: LiveData<List<MoviesItemDetails?>> = _moviesDetails

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<List<MoviesItem>> {
            override fun onResponse(call: Call<List<MoviesItem>>?, response: Response<List<MoviesItem>>?) {
                Log.d("MyLog", "OnResponse Success ${call.toString()}")
                _movies.postValue(response?.body()?)
            }
            override fun onFailure(call: Call<List<MoviesItem>>?, t: Throwable?) {
                Log.d("MyTag", "onFailure: ${t?.message}")
            }

        })
    }

    fun getMovieDetails(char_id: Int) {
        val response = mMoviesRepository.getMoviesDetails(char_id)
        response.enqueue(object : Callback<List<MoviesItemDetails>> {
            override fun onResponse(call: Call<List<MoviesItemDetails>>?, response: Response<List<MoviesItemDetails>>?) {
                Log.d("MyLog", "OnResponse Success ${call.toString()}")
                _moviesDetails.postValue(response?.body())
            }

            override fun onFailure(call: Call<List<MoviesItemDetails>>?, t: Throwable?) {
                Log.d("MyTag", "onFailure: ${t?.message}")
            }
        })
    }


}