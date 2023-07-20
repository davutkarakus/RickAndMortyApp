package com.davutkarakus.rickandmortyapp.di

import com.davutkarakus.rickandmortyapp.Constant.Constant.BASE_URL
import com.davutkarakus.rickandmortyapp.service.CharacterDetailApi
import com.davutkarakus.rickandmortyapp.service.CharactersApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitInstance():CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInstanceForDetail():CharacterDetailApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterDetailApi::class.java)
    }
}