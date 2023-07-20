package com.davutkarakus.rickandmortyapp.repo

import com.davutkarakus.rickandmortyapp.service.CharacterDetailApi
import com.davutkarakus.rickandmortyapp.service.CharactersApi
import javax.inject.Inject


class CharactersRepository @Inject constructor(private val api:CharactersApi,private val apiDetail:CharacterDetailApi){
    suspend fun getAllCharacters() = api.getAllCharacters()
    suspend fun getCharacter(id:Int) = apiDetail.getCharacter(id)
}
