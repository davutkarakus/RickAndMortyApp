package com.davutkarakus.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.davutkarakus.rickandmortyapp.constant.Constant.BASE_URL
import com.davutkarakus.rickandmortyapp.service.CharactersApi
import com.davutkarakus.rickandmortyapp.service.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstanceForFeed(retrofit: Retrofit):CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,CharactersDatabase::class.java,"CharactersDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: CharactersDatabase) = database.charactersDao()
}