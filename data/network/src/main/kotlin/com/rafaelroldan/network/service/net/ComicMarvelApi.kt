package com.rafaelroldan.network.service.net

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.result.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicMarvelApi {
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicByCharacter(
        @Path("characterId") characterId: Int,
    ): Response<ComicDto>
}