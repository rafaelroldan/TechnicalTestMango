package com.rafaelroldan.repository.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.Response
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<Response<CharacterDto>>

    fun getCharacterById(
        characterId: Int,
    ): Flow<Response<CharacterDto>>

    fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<Response<CharacterDto>>
}