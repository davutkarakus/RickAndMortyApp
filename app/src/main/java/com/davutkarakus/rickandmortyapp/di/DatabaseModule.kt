package com.davutkarakus.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.davutkarakus.rickandmortyapp.service.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CharactersDatabase::class.java,"CharactersDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: CharactersDatabase) = database.charactersDao()
}