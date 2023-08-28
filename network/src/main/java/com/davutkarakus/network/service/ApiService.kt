package com.davutkarakus.network.service

import com.davutkarakus.network.model.Characters
import com.davutkarakus.network.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // https://rickandmortyapi.com/api/character
    
    @GET("api/character")
    suspend fun getAllData() : Response<Characters>

    //https://rickandmortyapi.com/api/character/1
    @GET("api/character/{id}")
    suspend fun getData(@Path("id") id:Int) : Response<Result>
}