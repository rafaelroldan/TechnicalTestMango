package com.rafaelroldan.mappers

import com.rafaelroldan.dto.result.MarvelError

sealed interface MarvelResult<out SomeDomainModel>{
    data class Success<out SomeDomainModel>( val data: SomeDomainModel):
        MarvelResult<SomeDomainModel>
    data class Error<out SomeDomainModel>( val error: MarvelError): MarvelResult<SomeDomainModel>
}
