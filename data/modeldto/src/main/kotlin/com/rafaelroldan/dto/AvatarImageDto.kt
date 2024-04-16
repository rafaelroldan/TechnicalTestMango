package com.rafaelroldan.dto

data class AvatarImageDto(
    val extension: String,
    val path: String
)

//Todo - Test method
fun AvatarImageDto.imageUrl(): String{
    return this.path+"."+this.extension
}