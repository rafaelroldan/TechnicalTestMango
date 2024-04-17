package com.rafaelroldan.ui.characterlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafaelroldan.designsystem.components.CharacterRow
import com.rafaelroldan.designsystem.components.ComicRow
import com.rafaelroldan.designsystem.components.skeleton.SkeletonRow
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.ui.characterdetails.CharacterDetailsResult

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterItemClick:(Int) -> Unit,
) {

    val lazyState = rememberLazyListState()

    val isLoadingCharacterList : Boolean = viewModel.characterListResult.collectAsState(initial = CharacterListResult.Loading).value != CharacterListResult.Success

    Column {
        CharacterListView (
            characterList = viewModel.characterList,
            lazyState = lazyState,
            isLoadingList = isLoadingCharacterList,
            characterTotal = 0,
            onCharacterItemClick = {
                onCharacterItemClick(it)
            },
            onLoadMoreCharacter = {},
        )
    }
}

@Composable
fun CharacterListView(
    characterList: List<CharacterModel>,
    lazyState: LazyListState,
    isLoadingList: Boolean,
    characterTotal: Int,
    onCharacterItemClick: (Int)->Unit,
    onLoadMoreCharacter: ()->Unit,
    ) {
    if(characterList.isNotEmpty()){
        LazyColumn(
            state = lazyState,
            modifier = Modifier
        ){
            if(isLoadingList){
                items(10){
                    SkeletonRow(
                        modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                    )
                }
            }else{
                itemsIndexed(items = characterList){ index, character ->
                    CharacterRow(
                        modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                        characterName = character.name,
                        characterAvatar = character.avatar,
                        numComics = character.countListComics,
                        onViewCLickListener = {
                            onCharacterItemClick(character.id)
                        }
                    )
                    if(index >= characterList.size - 1 &&
                        characterList.size < characterTotal) {
                        onLoadMoreCharacter()
                    }
                }
            }
        }
    }
}
