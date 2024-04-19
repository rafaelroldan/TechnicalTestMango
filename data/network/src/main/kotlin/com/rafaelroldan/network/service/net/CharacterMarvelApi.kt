package com.rafaelroldan.network.service.net

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.MarvelResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterMarvelApi {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse<CharacterDto>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
    ): MarvelResponse<CharacterDto>
    @GET("/v1/public/characters")
    suspend fun getCharacterByStartName(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") nameStartsWith: String,
    ): MarvelResponse<CharacterDto>
}
