package com.rafaelroldan.ui.characterlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rafaelroldan.common.Constants
import com.rafaelroldan.designsystem.components.CharacterRow
import com.rafaelroldan.designsystem.components.SearchBarWidget
import com.rafaelroldan.designsystem.components.skeleton.SkeletonGridRow
import com.rafaelroldan.designsystem.components.skeleton.SkeletonRow
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterItemClick:(Int) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val characterList: LazyPagingItems<CharacterModel> =
        viewModel.characterSearchResults.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val isSearchBarVisible by viewModel.isSearchShowing.collectAsStateWithLifecycle()
    val search by viewModel.search.collectAsStateWithLifecycle()
    val paginationEnds by viewModel.paginationEnds.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.app_bar),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = ThemeConfig.theme.font.actionsComic,
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.toggleIsSearchShowing()
                        },
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->

        CharacterListView(
            modifier = Modifier.padding(paddingValues),
            characterList = characterList,
            lazyState = lazyState,
            searchText = search,
            isSearchBarVisible = isSearchBarVisible,
            paginationEnds = paginationEnds,
            onCharacterItemClick = {
                onCharacterItemClick(it)
            },
            onSearchQueryChanged = {
                viewModel.setSearch(it)
            },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterListView(
    modifier: Modifier = Modifier,
    characterList: LazyPagingItems<CharacterModel>,
    lazyState: LazyListState,
    isSearchBarVisible: Boolean,
    searchText: String,
    paginationEnds: Boolean = false,
    onCharacterItemClick: (Int)->Unit,
    onSearchQueryChanged: (String)->Unit,
    ) {
        LazyColumn(
            modifier = modifier,
            state = lazyState
        ){

            if (isSearchBarVisible) {
                stickyHeader {
                    SearchBarWidget(
                        query = searchText,
                        onQueryChanged = {
                            onSearchQueryChanged(it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.character_list_search_label),
                        placeholder = stringResource(R.string.character_list_search_placeholder),
                    )
                }
            }

            when(characterList.loadState.refresh) {
                LoadState.Loading -> {
                    items(Constants.PAGE_SIZE){
                        SkeletonGridRow(
                            modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                        )
                    }
                }
                is LoadState.Error -> {
                    //TODO implement error state
                }
                else -> {
                    items(characterList.itemCount){index ->
                        characterList[index]?.let { character ->
                            CharacterRow(
                                modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                                characterName = character.name,
                                characterAvatar = character.avatar,
                                numComics = character.countListComics,
                                onViewCLickListener = {
                                    onCharacterItemClick(character.id)
                                }
                            )
                        }
                    }

                    if(!paginationEnds){
                        item {
                            SkeletonRow(
                                modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                            )
                        }
                    }
                }
            }
        }
}
