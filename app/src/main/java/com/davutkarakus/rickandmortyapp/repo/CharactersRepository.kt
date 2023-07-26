package com.davutkarakus.rickandmortyapp.repo

import com.davutkarakus.rickandmortyapp.service.CharactersApi
import javax.inject.Inject


class CharactersRepository @Inject constructor(private val api:CharactersApi){
    suspend fun getAllCharacters() = api.getAllCharacters()
    suspend fun getCharacter(id:Int) = api.getCharacter(id)
}
