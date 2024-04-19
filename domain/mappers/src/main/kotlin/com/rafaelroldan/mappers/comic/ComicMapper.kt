package com.rafaelroldan.mappers.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.imageUrl
import com.rafaelroldan.mappers.Mapper
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.MarvelError
import com.rafaelroldan.dto.result.MarvelResponse
import com.rafaelroldan.mappers.MarvelResult
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class ComicMapper @Inject constructor(
) : Mapper<MarvelResponse<ComicDto>, MarvelResult<List<ComicModel>>> {
    override fun toDomainModel(value: MarvelResponse<ComicDto>): MarvelResult<List<ComicModel>> {

        return if(value.error){
            MarvelResult.Error(MarvelError.NetworkError(
                message = value.status,
                code = value.code
            ))
        } else {
            val result = arrayListOf<ComicModel>()
            value.data?.let { data ->
                Data(
                    count = data.count,
                    limit = data.limit,
                    offset = data.offset,
                    results = data.results.map {
                        result.add(
                            ComicModel(
                            id = it.id ,
                            title = it.title,
                            image = it.thumbnail.imageUrl() ,
                            date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                                .format(it.dates.first().date)
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
