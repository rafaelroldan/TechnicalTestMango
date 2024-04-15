package com.rafaelroldan.dto

data class GenericListDto(
    val available: Int,
    val collectionURI: String,
    val items: List<GenericModelDto>?,
    val returned: Int
)