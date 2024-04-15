package com.rafaelroldan.network.net

import com.rafaelroldan.dto.ComicDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicMarvelApi {
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicByCharacter(
        @Path("characterId") characterId: Int,
    ): Response<ComicDto>
}