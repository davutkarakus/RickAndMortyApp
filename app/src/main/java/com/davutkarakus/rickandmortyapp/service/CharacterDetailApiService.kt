package com.davutkarakus.rickandmortyapp.service

import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.model.Result
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDetailApiService {

    //https://rickandmortyapi.com/api/character/1
    private val BASE_URL = "https://rickandmortyapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharacterDetailApi::class.java)

  /*  fun getChar(id:Int) : Single<Result> {
        return api.getCharacter(id)
    }

   */


}