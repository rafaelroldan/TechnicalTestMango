package com.rafaelroldan.mappers.character

import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.mappers.Mapper
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.Response
import com.rafaelroldan.dto.result.Result
import javax.inject.Inject

class CharacterMapper @Inject constructor(
) : Mapper<Response<CharacterDto>, Result<CharacterModel>> {
    override fun toDomainModel(value: Response<CharacterDto>): Result<CharacterModel> {
        return Result(
            code = value.code,
            error = value.data?.let { false }?: true,
            data = value.data?.let { data ->
                Data(
                    count = data.count,
                    limit = data.limit,
                    offset = data.offset,
                    results = data.results.map {
                        CharacterModel(
                            id = it.id ,
                            name = it.name ,
                        )
                    },
                    total = data.total
                )
            }
        )
    }
}