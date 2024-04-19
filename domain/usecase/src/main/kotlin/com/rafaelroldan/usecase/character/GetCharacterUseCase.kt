package com.rafaelroldan.usecase.character

import com.rafaelroldan.mappers.MarvelResult
import com.rafaelroldan.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<MarvelResult<List<CharacterModel>>>

    fun getCharacterById(
        characterId: Int
    ): Flow<MarvelResult<List<CharacterModel>>>

    fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): Flow<MarvelResult<List<CharacterModel>>>
}
