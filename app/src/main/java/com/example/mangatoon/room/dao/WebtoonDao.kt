package com.example.mangatoon.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mangatoon.room.model.Webtoon

@Dao
interface WebtoonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWebtoons(webtoons: List<Webtoon>)

    @Query("SELECT * FROM webtoon_table WHERE title = :title LIMIT 1")
    suspend fun getWebtoonByTitle(title: String): Webtoon?

    @Query("SELECT * FROM webtoon_table")
    suspend fun getAllWebtoons(): List<Webtoon>

    @Query("SELECT * FROM webtoon_table WHERE isFavorite = 1")
    suspend fun getFavorites(): List<Webtoon>

    @Query("UPDATE webtoon_table SET isFavorite = :isFavorite WHERE id = :webtoonId")
    suspend fun updateFavoriteStatus(webtoonId: Int, isFavorite: Boolean)


}