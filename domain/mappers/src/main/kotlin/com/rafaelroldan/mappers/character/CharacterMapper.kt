package com.rafaelroldan.mappers.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.imageUrl
import com.rafaelroldan.mappers.Mapper
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.MarvelError
import com.rafaelroldan.dto.result.MarvelResponse
import com.rafaelroldan.mappers.MarvelResult
import javax.inject.Inject

class CharacterMapper @Inject constructor(
) : Mapper<MarvelResponse<CharacterDto>, MarvelResult<List<CharacterModel>>> {
    override fun toDomainModel(value: MarvelResponse<CharacterDto>): MarvelResult<List<CharacterModel>> {
        return if(value.error){
            MarvelResult.Error(MarvelError.NetworkError(
                message = value.status,
                code = value.code
            ))
        } else {
            val result = arrayListOf<CharacterModel>()
            value.data?.let { data ->
                Data(
                    count = data.count,
                    limit = data.limit,
                    offset = data.offset,
                    results = data.results.map {
                        result.add(
                            CharacterModel(
                                id = it.id ,
                                name = it.name ,
                                avatar = it.thumbnail.imageUrl(),
                                description = it.description,
                                countListComics = it.comics.items?.size ?: 0
                            )
                        )
                    },
                    total = data.total
                )
            }
            return MarvelResult.Success(result)
        }
    }
}
