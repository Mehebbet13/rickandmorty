package com.example.abbtechtestapplication.api

import com.example.abbtechtestapplication.models.MainInfo
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.abbtechtestapplication.models.Character
import retrofit2.Response
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): MainInfo

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Character

    @GET("character")
    suspend fun filterCharacter(
        @Query("name") name: String,
    ): MainInfo
}