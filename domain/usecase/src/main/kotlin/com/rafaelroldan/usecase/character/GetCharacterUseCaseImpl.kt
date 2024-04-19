package com.rafaelroldan.usecase.character

import com.rafaelroldan.mappers.MarvelResult
import com.rafaelroldan.mappers.character.CharacterMapper
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.repository.character.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val characterMapper: CharacterMapper,
): GetCharacterUseCase {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<MarvelResult<List<CharacterModel>>> {
        return characterRepository.getAllCharacter(offset, limit)
            .map {
                characterMapper.toDomainModel(it)
            }

    }

    override fun getCharacterById(characterId: Int): Flow<MarvelResult<List<CharacterModel>>> {
        return characterRepository.getCharacterById(characterId)
            .map {
                characterMapper.toDomainModel(it)
            }
    }

    override fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<MarvelResult<List<CharacterModel>>> {
        return characterRepository.getCharacterByStartName(
            offset = offset,
            limit = limit,
            nameStartsWith = nameStartsWith
        ).map {
            characterMapper.toDomainModel(it)
        }
    }

}
