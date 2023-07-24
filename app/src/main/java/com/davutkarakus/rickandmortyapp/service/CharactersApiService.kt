package com.davutkarakus.rickandmortyapp.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApiService {
    // https://rickandmortyapi.com/api/character

    private val BASE_URL = "https://rickandmortyapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharactersApi::class.java)

   // fun getData() : Single<Characters> {
    //    return api.getAllCharacters()
   // }

}