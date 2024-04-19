package com.rafaelroldan.network.remote.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.result.MarvelResponse
import com.rafaelroldan.network.service.net.CharacterMarvelApi
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val service: CharacterMarvelApi
) : CharacterRemoteDataSource {
    override suspend fun getAllCharacter(
        offset: Int, limit: Int
    ): MarvelResponse<CharacterDto> = service.getAllCharacters(offset, limit)


    override suspend fun getCharacterById(
        characterId: Int
    ): MarvelResponse<CharacterDto> = service.getCharacterById(characterId)

    override suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): MarvelResponse<CharacterDto> = service.getCharacterByStartName(offset, limit, nameStartsWith)

}
