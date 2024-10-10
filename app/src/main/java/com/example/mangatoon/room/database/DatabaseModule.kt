package com.example.mangatoon.room.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideContext(application: android.app.Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideWebtoonDatabase(context: Context): WebtoonDatabase {
        return Room.databaseBuilder(
            context,
            WebtoonDatabase::class.java,
            "webtoon_database"
        ).build()
    }
}