package com.rafaelroldan.repository.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.MarvelResponse
import com.rafaelroldan.network.remote.character.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterRemoteDataSource
): CharacterRepository {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<MarvelResponse<CharacterDto>> =
        flow {
            emit( network.getAllCharacter(offset,limit))
        }.catch {
            emit(MarvelResponse(error = true))
        }
    override fun getCharacterById(characterId: Int): Flow<MarvelResponse<CharacterDto>> =
        flow {
            emit(network.getCharacterById(characterId))
        }.catch {
            emit(MarvelResponse(error = true))
        }

    override fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<MarvelResponse<CharacterDto>> =
        flow {
            emit(
                network.getCharacterByStartName(
                    offset = offset,
                    limit = limit,
                    nameStartsWith = nameStartsWith
                )
            )
        }.catch {
            emit(MarvelResponse(error = true))
        }
}
