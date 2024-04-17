package com.rafaelroldan.dto

data class AvatarImageDto(
    val extension: String,
    val path: String
)

fun AvatarImageDto.imageUrl(): String{
    return this.path+"."+this.extension
}
