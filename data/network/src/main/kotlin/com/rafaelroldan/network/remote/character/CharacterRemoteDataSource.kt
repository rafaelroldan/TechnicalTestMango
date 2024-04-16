package com.rafaelroldan.network.remote.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.Response

interface CharacterRemoteDataSource {
    suspend fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Response<CharacterDto>

    suspend fun getCharacterById(
        characterId: Int,
    ): Response<CharacterDto>

    suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): Response<CharacterDto>
}