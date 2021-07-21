package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.data.db.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {
    suspend fun saveMovie (result : Result) = moviesRepository.insert(result)
}