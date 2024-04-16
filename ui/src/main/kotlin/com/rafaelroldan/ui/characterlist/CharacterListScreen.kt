package com.rafaelroldan.ui.characterlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafaelroldan.designsystem.components.CharacterRow
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterItemClick:(Int) -> Unit,
) {

    val lazyState = rememberLazyListState()

    LaunchedEffect(viewModel.characterListResult) {
        viewModel.characterListResult.collect {
            when(it){
                CharacterListResult.Loading -> {}
                CharacterListResult.Success -> {}
                CharacterListResult.Error -> {}
            }
        }
    }

    Column {
        CharacterListView (
            characterList = viewModel.characterList,
            lazyState = lazyState
        ){
            onCharacterItemClick(it)
        }
    }
}

@Composable
fun CharacterListView(
    characterList: List<CharacterModel>,
    lazyState: LazyListState,
    onCharacterItemClick: (Int)->Unit,
) {
    if(characterList.isNotEmpty()){
        LazyColumn(
            state = lazyState,
            modifier = Modifier
        ){
            items(items = characterList){ character ->
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
    }
}
