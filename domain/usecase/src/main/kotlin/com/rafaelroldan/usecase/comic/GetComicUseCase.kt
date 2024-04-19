package com.rafaelroldan.usecase.comic

import com.rafaelroldan.mappers.MarvelResult
import com.rafaelroldan.model.ComicModel
import kotlinx.coroutines.flow.Flow

interface GetComicUseCase {
    fun getComicByCharacter(
        characterId: Int
    ): Flow<MarvelResult<List<ComicModel>>>
}
