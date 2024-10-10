package com.example.mangatoon.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mangatoon.room.dao.WebtoonDao
import com.example.mangatoon.room.model.Webtoon


@Database(entities = [Webtoon::class], version = 1, exportSchema = false)
abstract class WebtoonDatabase : RoomDatabase() {
    abstract fun webtoonDao(): WebtoonDao

    companion object {
        @Volatile
        private var INSTANCE: WebtoonDatabase? = null

        fun getDatabase(context: Context): WebtoonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WebtoonDatabase::class.java,
                    "webtoon_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

