package com.rafaelroldan.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: GenericListDto,
    val events: GenericListDto,
    val series: GenericListDto,
    val stories: GenericListDto,
    val urls: List<UrlDto>,
    val modified: String,
    val resourceURI: String
)