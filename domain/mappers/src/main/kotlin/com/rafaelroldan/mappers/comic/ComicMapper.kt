package com.rafaelroldan.mappers.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.imageUrl
import com.rafaelroldan.mappers.Mapper
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.Response
import com.rafaelroldan.dto.result.Result
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

//TODO - Testing
class ComicMapper @Inject constructor(
) : Mapper<Response<ComicDto>, Result<ComicModel>> {
    override fun toDomainModel(value: Response<ComicDto>): Result<ComicModel> {
        return Result(
            code = value.code,
            error = value.data?.let { false } ?: true,
            data = value.data?.let { data ->
                Data(
                    count = data.count,
                    limit = data.limit,
                    offset = data.offset,
                    results = data.results.map { comic ->
                        ComicModel(
                            id = comic.id ,
                            title = comic.title,
                            image = comic.thumbnail.imageUrl() ,
                            date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                                .format(comic.dates.first().date)
                        )
                    },
                    total = data.total
                )
            }
        )
    }
}
