package com.rafaelroldan.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
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

    var paginationEnds = MutableStateFlow(false)

    val isSearchShowing = _isSearchShowing.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val productsSearchResults = search.debounce(300.milliseconds).flatMapLatest { query ->
        Pager(
            PagingConfig(
                prefetchDistance = Constants.PAGE_SIZE,
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
            )
        ) {
            CharacterPagingSource(
                repository = characterUseCase,
                search = query,
            ){
                paginationEnds.value = it
            }
        }.flow
    }

    fun setSearch(query: String) {
        _search.value = query
    }

    fun toggleIsSearchShowing() {
        _isSearchShowing.value = !_isSearchShowing.value
    }
}
