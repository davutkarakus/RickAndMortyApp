package com.davutkarakus.rickandmortyapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.davutkarakus.rickandmortyapp.model.Result

@Dao
interface CharactersDao {
    @Insert
    suspend fun insertAll(vararg characters:Result)
    @Query("SELECT * FROM results")
    suspend fun getAllCharacters() : List<Result>
    @Query("DELETE FROM results")
    suspend fun deleteAllCharacters()

    //Detay sayfası da roomdan çekilmek istenirse.Aşağıdaki suspend fonskiyon kullanılabilir.
    @Query("SELECT * FROM results WHERE id = :charId")
    suspend fun getCharacter(charId:Int) : Result
}