package com.example.myapp.viewmodel

import com.example.myapp.model.repository.MoviesDBRepository
import com.example.myapp.model.repository.MoviesDBRepositoryImpl

class MoviesViewModel {

    private val mMoviesRepository : MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies(): String {
        return mMoviesRepository.getMovies()
    }
}