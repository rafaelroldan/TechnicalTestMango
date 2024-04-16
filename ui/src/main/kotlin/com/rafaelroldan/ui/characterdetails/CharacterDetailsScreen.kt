package com.rafaelroldan.ui.characterdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafaelroldan.designsystem.components.ComicRow
import com.rafaelroldan.designsystem.components.LandscapeImage
import com.rafaelroldan.designsystem.theme.TechnicalTestMangoTheme
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.model.ComicModel

@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    characterId: Int
) {

    viewModel.getCharacter(characterId)

    val lazyState = rememberLazyListState()

    TechnicalTestMangoTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CharacterDetailsView(
                character = viewModel.character,
                comicList = viewModel.comicsList,
                lazyState = lazyState
            )
        }
    }
}

@Composable
fun CharacterDetailsView(
    character: CharacterModel?,
    comicList: List<ComicModel> = arrayListOf(),
    lazyState: LazyListState,
) {
    if(character != null) {
        LazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyState,
        ) {
            item {
                LandscapeImage(image = character.avatar)

                Text(
                    text = character.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = ThemeConfig.theme.spacing.sizeSpacing20)
                )

                Text(
                    text = character.description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = ThemeConfig.theme.spacing.sizeSpacing20,
                            bottom = ThemeConfig.theme.spacing.sizeSpacing20
                        )
                )
            }

            items(comicList){ comic ->
                ComicRow(
                    modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                    name = comic.title,
                    avatar = comic.image,
                    description = comic.date,
                )
            }
        }
    }
}
