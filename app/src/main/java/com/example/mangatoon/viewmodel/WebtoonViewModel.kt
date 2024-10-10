package com.example.mangatoon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangatoon.repository.WebtoonRepo
import com.example.mangatoon.room.model.Webtoon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebtoonViewModel @Inject constructor(private val repository: WebtoonRepo): ViewModel(){

    private val _webtoonList = MutableStateFlow<List<Webtoon>>(emptyList())
    val webtoonList: StateFlow<List<Webtoon>> = _webtoonList

    init {
        viewModelScope.launch {
            repository.insertSampleWebtoons()
            fetchWebtoons() // Fetch the webtoons after insertion
        }
    }

    private suspend fun fetchWebtoons() {
        _webtoonList.value=repository.getAllWebtoons()
    }

}