package com.rafaelroldan.repository.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.network.remote.character.CharacterRemoteDataSource
import com.rafaelroldan.dto.result.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterRemoteDataSource
): CharacterRepository {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getAllCharacter(offset,limit))
        }.catch {
            emit(Response(error = true))
        }
    override fun getCharacterById(characterId: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getCharacterById(characterId))
        }.catch {
            emit(Response(error = true))
        }

    override fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<Response<CharacterDto>> =
        flow {
            emit(
                network.getCharacterByStartName(
                    offset = offset,
                    limit = limit,
                    nameStartsWith = nameStartsWith
                )
            )
        }.catch {
            emit(Response(error = true))
        }
}
