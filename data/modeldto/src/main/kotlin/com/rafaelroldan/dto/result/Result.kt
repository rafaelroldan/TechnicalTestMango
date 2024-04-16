package com.rafaelroldan.dto.result

data class Result<SomeDomainModel>(
    val code: Int,
    val error: Boolean = false,
    val data: Data<SomeDomainModel>?,
)