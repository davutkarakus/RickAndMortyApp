package com.davutkarakus.rickandmortyapp.repo

import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.service.CharactersApi
import com.davutkarakus.rickandmortyapp.service.CharactersDao
import javax.inject.Inject


class CharactersRepository @Inject constructor(private val api:CharactersApi,private val dao:CharactersDao){
    suspend fun getAllCharacters() = api.getAllCharacters()
    suspend fun getCharacter(id:Int) = api.getCharacter(id)
    suspend fun getAllCharactersDao() = dao.getAllCharacters()
    suspend fun deleteAllCharactersDao() = dao.deleteAllCharacters()
    suspend fun insertCharactersDao(vararg characters:Result) = dao.insertAll(*characters)
}
