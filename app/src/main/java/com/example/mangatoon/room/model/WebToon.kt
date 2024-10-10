package com.example.mangatoon.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "webtoon_table")
data class Webtoon(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val imageUrl: String,
    val description: String,
    val isFavorite: Boolean = false // Add this field
)
