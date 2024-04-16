package com.rafaelroldan.usecase.character

import com.rafaelroldan.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<com.rafaelroldan.dto.result.Result<CharacterModel>>

    fun getCharacterById(
        characterId: Int
    ): Flow<com.rafaelroldan.dto.result.Result<CharacterModel>>

    fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): Flow<com.rafaelroldan.dto.result.Result<CharacterModel>>
}