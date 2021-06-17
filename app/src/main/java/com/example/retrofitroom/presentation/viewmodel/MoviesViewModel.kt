package com.example.retrofitroom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.common.ERROR
import com.example.retrofitroom.domain.entity.MoviesResponse
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    val moviesNews: MutableLiveData<MoviesResponse> = MutableLiveData()
    val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch {
        val response = getMoviesUseCase.execute()
        val body = response.body()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }
        moviesNews.postValue(body)
    }
}