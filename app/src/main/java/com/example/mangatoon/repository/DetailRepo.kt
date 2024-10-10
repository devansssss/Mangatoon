package com.example.mangatoon.repository

import com.example.mangatoon.room.database.WebtoonDatabase
import com.example.mangatoon.room.model.Webtoon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepo @Inject constructor(private val webtoondb : WebtoonDatabase){
    suspend fun getWebtoonDetails(title: String): Webtoon?{
        return webtoondb.webtoonDao().getWebtoonByTitle(title)
    }

    suspend fun updateWebtoon(webtoon: Webtoon){
        webtoondb.webtoonDao().updateFavoriteStatus(webtoon.id,webtoon.isFavorite)
    }
}