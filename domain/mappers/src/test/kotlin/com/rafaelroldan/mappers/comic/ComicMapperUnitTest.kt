package com.rafaelroldan.mappers.comic

import com.rafaelroldan.dto.AvatarImageDto
import com.rafaelroldan.dto.ComicDateDto
import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.MarvelResponse
import com.rafaelroldan.mappers.MarvelResult
import com.rafaelroldan.model.ComicModel
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.Date

class ComicMapperUnitTest {
    @Test
    fun testComicMapper() {

        val mapper = ComicMapper()
        val model = ComicModel(
            id = 0,
            title = "String",
            date = "17 abr 2024",
            image = "2.1"
        )
        val comicDate = ComicDateDto(
            type = "String",
            date = SimpleDateFormat("dd/MM/yyyy").parse("17/04/2024") ?: Date(),
        )

        val dto = ComicDto(
            id = 0,
            digitalId = 0,
            title = "String",
            issueNumber = 0.0,
            variantDescription = "String",
            description = "String",
            modified = SimpleDateFormat("dd/MM/yyyy").parse("17/04/2024") ?: Date(),
            isbn = "String",
            upc = "String",
            diamondCode = "String",
            ean = "String",
            issn = "String",
            format = "String",
            pageCount = 0,
            dates = arrayListOf(comicDate),
            thumbnail = AvatarImageDto("1","2"),
        )

        val response = MarvelResponse<ComicDto>(
            data = Data<ComicDto>(
                count = 0,
                limit = 0,
                offset = 0,
                results = arrayListOf(dto),
                total=  0,
            )
        )

        assertEquals(MarvelResult.Success(arrayListOf(model)),mapper.toDomainModel(response))
    }
}
