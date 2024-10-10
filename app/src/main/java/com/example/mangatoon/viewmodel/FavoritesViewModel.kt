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
class FavoritesViewModel @Inject constructor(private val repository: WebtoonRepo): ViewModel() {
    private val _favoritesList = MutableStateFlow<List<Webtoon>>(emptyList())
    val favoritesList: StateFlow<List<Webtoon>> = _favoritesList

    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            _favoritesList.value = repository.getFavorites()
        }
    }
}