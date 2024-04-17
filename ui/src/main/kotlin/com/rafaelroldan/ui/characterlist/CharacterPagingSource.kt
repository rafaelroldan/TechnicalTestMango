package com.rafaelroldan.ui.characterlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafaelroldan.common.Constants
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.usecase.character.GetCharacterUseCase

class CharacterPagingSource(
    private val repository: GetCharacterUseCase,
    val search: String,
): PagingSource<Int, CharacterModel>() {
    companion object {
        private const val INITIAL_LOAD_SIZE = 0
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val position = params.key ?: INITIAL_LOAD_SIZE

            var result: List<CharacterModel> = arrayListOf()
            repository.getAllCharacter(
                offset = position * Constants.PAGE_SIZE,
                limit = Constants.PAGE_SIZE
            ).collect{
                result = it.data?.results ?: arrayListOf()
            }

            val nextKey = if (result.isEmpty()) {
                null
            } else {
                position + 1
            }

            return LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}