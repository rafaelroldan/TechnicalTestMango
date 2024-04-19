package com.rafaelroldan.network.remote.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.MarvelResponse

interface CharacterRemoteDataSource {
    suspend fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): MarvelResponse<CharacterDto>

    suspend fun getCharacterById(
        characterId: Int,
    ): MarvelResponse<CharacterDto>

    suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): MarvelResponse<CharacterDto>
}
