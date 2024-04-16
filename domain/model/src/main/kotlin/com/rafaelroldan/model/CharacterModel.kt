package com.rafaelroldan.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val listComics: List<ComicModel> = arrayListOf(),
)