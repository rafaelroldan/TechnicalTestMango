package com.rafaelroldan.mappers.character

import com.rafaelroldan.dto.AvatarImageDto
import com.rafaelroldan.dto.CharacterDto
import com.rafaelroldan.dto.GenericListDto
import com.rafaelroldan.dto.result.Data
import com.rafaelroldan.dto.result.Response
import com.rafaelroldan.dto.result.Result
import com.rafaelroldan.model.CharacterModel
import org.junit.Test

import org.junit.Assert.*

class CharacterMapperUnitTest {
    @Test
    fun testCharacterMapper() {

        val mapper = CharacterMapper()
        val model = CharacterModel(
            id = 0,
            name = "String",
            avatar = "2.1",
            description = "String"
        )
        val dto = CharacterDto(
            id = 0,
            name = "String",
            description = "String",
            thumbnail = AvatarImageDto("1","2"),
            comics =  GenericListDto( 0,"",null,0),
            events = GenericListDto( 0,"",null,0),
            series =  GenericListDto( 0,"",null,0),
            stories =  GenericListDto( 0,"",null,0),
            urls = arrayListOf(),
            modified =  "String",
            resourceURI =  "String"
        )

        val response = Response<CharacterDto>(
            data = Data<CharacterDto>(
                count = 0,
                limit = 0,
                offset = 0,
                results = arrayListOf(dto),
                total=  0,
            )
        )

        val result = Result<CharacterModel>(
            code = 200,
            error = false,
            data = Data<CharacterModel>(
                count = 0,
                limit = 0,
                offset = 0,
                results = arrayListOf(model),
                total=  0,
            )
        )

        assertEquals(result,mapper.toDomainModel(response))
    }
}
