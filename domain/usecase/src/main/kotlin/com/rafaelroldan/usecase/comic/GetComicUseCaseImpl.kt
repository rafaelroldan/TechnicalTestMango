package com.rafaelroldan.usecase.comic

import com.rafaelroldan.mappers.comic.ComicMapper
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.repository.comic.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicUseCaseImpl @Inject constructor(
    private val comicRepository: ComicRepository,
    private val comicMapper: ComicMapper,
): GetComicUseCase {
    override fun getComicByCharacter(characterId: Int): Flow<com.rafaelroldan.dto.result.Result<ComicModel>> {
        return comicRepository.getComicByCharacter(characterId)
            .map {
                comicMapper.toDomainModel(it)
            }
    }

}