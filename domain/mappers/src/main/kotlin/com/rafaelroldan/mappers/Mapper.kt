package com.rafaelroldan.mappers

interface Mapper<ResponseSomeDto, ResponseSomeModel> {

    fun toDomainModel(value: ResponseSomeDto): ResponseSomeModel
}
