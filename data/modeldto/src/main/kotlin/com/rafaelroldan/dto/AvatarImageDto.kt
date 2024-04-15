package com.rafaelroldan.dto

data class Thumbnail(
    val extension: String,
    val path: String
)

//Todo - Test method
fun Thumbnail.imageUrl(): String{
    return this.path+"."+this.extension
}