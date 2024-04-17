package com.rafaelroldan.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rafaelroldan.common.Constants
import com.rafaelroldan.usecase.character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _search = MutableStateFlow("")

    val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    private val _isSearchShowing = MutableStateFlow(false)

    val isSearchShowing = _isSearchShowing.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    var paginationEnds = MutableStateFlow(false)

    private val _isErrorShowing = MutableStateFlow(false)

    val isErrorShowing = _isErrorShowing.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val characterSearchResults = search.debounce(300.milliseconds).flatMapLatest {
        pager.flow
    }.cachedIn(viewModelScope)

    private val pager = Pager(
        PagingConfig(
            prefetchDistance = Constants.PAGE_SIZE,
            pageSize = Constants.PAGE_SIZE,
            enablePlaceholders = false,
        )
    ) {
        CharacterPagingSource(
            search = _search.value,
            onPaginationEnd = {
                paginationEnds.value = it
            },
            onGetCharacters = { offset, limit ->
              characterUseCase.getAllCharacter(offset,limit)
            },
            onGetCharacterByName = { offset, limit, name ->
                characterUseCase.getCharacterByStartName(offset,limit,name)
            }
        )
    }

    fun setSearch(query: String) {
        _search.value = query
    }

    fun toggleIsSearchShowing() {
        _isSearchShowing.value = !_isSearchShowing.value
    }

    fun toggleIsErrorShowing(isVisible: Boolean) {
        _isErrorShowing.value = isVisible
    }
}
