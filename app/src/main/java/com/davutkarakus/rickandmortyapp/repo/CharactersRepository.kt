package com.davutkarakus.rickandmortyapp.repo

import com.davutkarakus.network.model.Result
import com.davutkarakus.network.service.ApiService
import com.davutkarakus.rickandmortyapp.service.CharactersDao
import javax.inject.Inject


class CharactersRepository @Inject constructor(private val api:ApiService,private val dao:CharactersDao){
    suspend fun getAllCharacters() = api.getAllData()
    suspend fun getCharacter(id:Int) = api.getData(id)
    suspend fun getAllCharactersDao() = dao.getAllCharacters()
    suspend fun deleteAllCharactersDao() = dao.deleteAllCharacters()
    suspend fun insertCharactersDao(vararg characters:Result) = dao.insertAll(*characters)

    //Detay sayfası da roomdan çekilmek istenirse.Aşağıdaki suspend fonskiyon kullanılabilir.
    suspend fun getCharacterFromRoom(charId:Int) = dao.getCharacter(charId)
}
