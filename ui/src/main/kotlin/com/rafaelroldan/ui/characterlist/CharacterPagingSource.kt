package com.rafaelroldan.ui.characterlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafaelroldan.common.Constants
import com.rafaelroldan.dto.result.Result
import com.rafaelroldan.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class CharacterPagingSource(
    val search: String,
    private val onPaginationEnd: ((Boolean)->Unit)? = null,
    private val onGetCharacters: ((Int, Int) -> Flow<Result<CharacterModel>>),
    private val onGetCharacterByName: ((Int, Int, String)-> Flow<Result<CharacterModel>>),
): PagingSource<Int, CharacterModel>() {
    companion object {
        private const val INITIAL_LOAD_SIZE = 0
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        onPaginationEnd?.invoke(false)
        return try {
            val position = params.key ?: INITIAL_LOAD_SIZE

            var result: List<CharacterModel> = arrayListOf()

            if(search.isEmpty()){
                onGetCharacters(position * Constants.PAGE_SIZE, Constants.PAGE_SIZE)
                    .collect{
                        result = it.data?.results ?: arrayListOf()
                    }
            }else{

                onGetCharacterByName(position * Constants.PAGE_SIZE, Constants.PAGE_SIZE, search)
                    .collect{
                        result = it.data?.results ?: arrayListOf()
                    }
            }

            val nextKey = if (result.isEmpty()) {
                onPaginationEnd?.invoke(true)
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
