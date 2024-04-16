package com.rafaelroldan.network.remote.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.network.service.net.ComicMarvelApi
import com.rafaelroldan.dto.result.Response
import javax.inject.Inject

class ComicRemoteDataSourceImpl @Inject constructor(
    private val service: ComicMarvelApi
): ComicRemoteDataSource {
    override suspend fun getComicByCharacter(characterId: Int): Response<ComicDto> =
        service.getComicByCharacter(characterId)

}