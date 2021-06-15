package com.example.retrofitroom.data.db.cache

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.Result

interface MoviesInterface {
    suspend fun insert(movie: Result)
    fun getAllMovie(): LiveData<List<Result>>
    suspend fun deleteMovie(movie: Result)
}