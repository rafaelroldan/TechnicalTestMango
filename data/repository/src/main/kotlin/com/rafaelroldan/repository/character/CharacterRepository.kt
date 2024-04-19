package com.rafaelroldan.repository.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.MarvelResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<MarvelResponse<CharacterDto>>

    fun getCharacterById(
        characterId: Int,
    ): Flow<MarvelResponse<CharacterDto>>

    fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<MarvelResponse<CharacterDto>>
}
