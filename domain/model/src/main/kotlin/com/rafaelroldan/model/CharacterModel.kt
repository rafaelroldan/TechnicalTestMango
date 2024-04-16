package com.rafaelroldan.model

data class CharacterModel(
    val id: Int,
    val name: String = "",
    val avatar: String = "",
    val description: String = "",
    val countListComics: Int = 0,
)
