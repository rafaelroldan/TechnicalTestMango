package com.rafaelroldan.usecase.comic

import com.rafaelroldan.model.ComicModel
import kotlinx.coroutines.flow.Flow

interface GetComicUseCase {
    fun getComicByCharacter(
        characterId: Int
    ): Flow<com.rafaelroldan.dto.result.Result<ComicModel>>
}
