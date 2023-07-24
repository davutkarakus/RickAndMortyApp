package com.davutkarakus.rickandmortyapp.service

import com.davutkarakus.rickandmortyapp.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailApi {

    //https://rickandmortyapi.com/api/character/1

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id:Int) : Response<Result>

}