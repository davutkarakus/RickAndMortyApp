package com.davutkarakus.rickandmortyapp.service

import com.davutkarakus.rickandmortyapp.model.Characters
import io.reactivex.Single
import retrofit2.http.GET

interface CharactersApi {
    // https://rickandmortyapi.com/api/character

    @GET("api/character")
    fun getAllCharacters() : Single<Characters>
}