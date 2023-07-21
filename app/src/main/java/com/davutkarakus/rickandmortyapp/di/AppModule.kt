package com.davutkarakus.rickandmortyapp.di

import com.davutkarakus.rickandmortyapp.constant.Constant.BASE_URL
import com.davutkarakus.rickandmortyapp.service.CharacterDetailApi
import com.davutkarakus.rickandmortyapp.service.CharactersApi
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
    fun getRetrofitServiceInstanceForDetail(retrofit: Retrofit):CharacterDetailApi {
        return retrofit.create(CharacterDetailApi::class.java)
    }

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
}