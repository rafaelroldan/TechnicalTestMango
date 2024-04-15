package com.rafaelroldan.network.net

import com.rafaelroldan.dto.CharacterDto
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterMarvelApi {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharacterDto>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
    ): Response<CharacterDto>
    @GET("/v1/public/characters")
    suspend fun getCharacterByStartName(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") nameStartsWith: String,
    ): Response<CharacterDto>
}