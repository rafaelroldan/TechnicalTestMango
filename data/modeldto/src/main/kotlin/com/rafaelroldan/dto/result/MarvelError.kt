package com.rafaelroldan.dto.result

sealed class MarvelError : Throwable(){
    data class NetworkError(override val message: String = "", val code : Int = 0) : MarvelError()
}