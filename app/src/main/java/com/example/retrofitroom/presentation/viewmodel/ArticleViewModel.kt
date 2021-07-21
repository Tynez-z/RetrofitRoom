package com.example.retrofitroom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.common.ERROR
import com.example.retrofitroom.domain.entity.Result
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase
) : ViewModel() {

    private val moviesNews: MutableLiveData<List<Result>> = MutableLiveData()
    private val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch {
        val response = getMoviesUseCase.getMovies()
        val body = response.body()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }
        moviesNews.postValue(body!!.results)
    }

    fun saveArticle(movie: Result) = viewModelScope.launch {
        saveMoviesUseCase.saveMovie(movie)
    }
}