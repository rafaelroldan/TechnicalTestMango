package com.rafaelroldan.repository.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.result.MarvelResponse
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComicByCharacter(
        characterId: Int
    ): Flow<MarvelResponse<ComicDto>>
}
