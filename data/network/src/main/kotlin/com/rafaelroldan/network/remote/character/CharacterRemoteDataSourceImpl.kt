package com.rafaelroldan.network.remote.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.network.service.net.CharacterMarvelApi
import com.rafaelroldan.dto.result.Response
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val service: CharacterMarvelApi
) : CharacterRemoteDataSource {
    override suspend fun getAllCharacter(offset: Int, limit: Int): Response<CharacterDto> =
        service.getAllCharacters(offset,limit)

    override suspend fun getCharacterById(characterId: Int): Response<CharacterDto> =
        service.getCharacterById(characterId)

    override suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Response<CharacterDto> =
        service.getCharacterByStartName(
            offset = offset,
            limit = limit,
            nameStartsWith = nameStartsWith
        )

}
