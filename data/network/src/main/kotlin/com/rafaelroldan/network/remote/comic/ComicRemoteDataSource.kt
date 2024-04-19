package com.rafaelroldan.network.remote.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.result.MarvelResponse

interface ComicRemoteDataSource {
    suspend fun getComicByCharacter(
        characterId: Int
    ): MarvelResponse<ComicDto>
}
