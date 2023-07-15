package com.davutkarakus.rickandmortyapp.service

import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.model.Result
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterDetailApi {

    //https://rickandmortyapi.com/api/character/1

    @GET("api/character/{id}")
    fun getCharacter(@Path("id") id:Int) : Single<Result>

}