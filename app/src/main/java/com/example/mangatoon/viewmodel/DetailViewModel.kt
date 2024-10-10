package com.example.mangatoon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangatoon.repository.DetailRepo
import com.example.mangatoon.room.model.Webtoon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepo: DetailRepo) : ViewModel() {
    private val _webtoonDetails = MutableStateFlow<Webtoon?>(null)
    val webtoonDetails: StateFlow<Webtoon?> get() = _webtoonDetails

    fun fetchWebtoonDetails(title: String) {
        viewModelScope.launch {
            _webtoonDetails.value = detailRepo.getWebtoonDetails(title)
        }
    }

    fun toggleFavorite(webtoon: Webtoon){
        viewModelScope.launch {
            val updatedwebtoon = webtoon.copy(isFavorite = !webtoon.isFavorite)
            detailRepo.updateWebtoon(updatedwebtoon)
            _webtoonDetails.value = updatedwebtoon
        }
    }
}