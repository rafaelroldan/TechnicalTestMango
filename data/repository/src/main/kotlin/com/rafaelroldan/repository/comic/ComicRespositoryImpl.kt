package com.rafaelroldan.repository.comic

import com.rafaelroldan.dto.ComicDto
import com.rafaelroldan.network.remote.comic.ComicRemoteDataSource
import com.rafaelroldan.dto.result.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val network: ComicRemoteDataSource
): ComicRepository {
    override fun getComicByCharacter(characterId: Int): Flow<Response<ComicDto>> =
        flow {
            emit(network.getComicByCharacter(characterId))
        }.catch {
            emit(Response(error = true))
        }
}