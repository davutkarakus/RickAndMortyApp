package com.davutkarakus.rickandmortyapp.service

import com.davutkarakus.rickandmortyapp.model.Characters
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    // https://rickandmortyapi.com/api/character

    @GET("api/character")
    suspend fun getAllCharacters() : Response<Characters>
}