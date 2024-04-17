package com.rafaelroldan.ui.characterdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.rafaelroldan.common.Constants
import com.rafaelroldan.designsystem.components.ComicRow
import com.rafaelroldan.designsystem.components.LandscapeImage
import com.rafaelroldan.designsystem.components.skeleton.SkeletonLandscapeImage
import com.rafaelroldan.designsystem.components.skeleton.SkeletonRow
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.model.ComicModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    characterId: Int,
    onBackNavigation:() -> Unit,
) {

    LaunchedEffect(Unit){
        viewModel.getCharacter(characterId)
    }

    val lazyState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val isLoadingCharacter : Boolean = viewModel.characterResult.collectAsState(initial = CharacterDetailsResult.Loading).value != CharacterDetailsResult.Success
    val isLoadingComics : Boolean = viewModel.comicsListResult.collectAsState(initial = ComicListResult.Loading).value != ComicListResult.Success

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onBackNavigation.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        CharacterDetailsView(
            character = viewModel.character,
            comicList = viewModel.comicsList,
            lazyState = lazyState,
            padding = paddingValues,
            isLoadingHeader = isLoadingCharacter,
            isLoadingList = isLoadingComics
        )
    }
}

@Composable
fun CharacterDetailsView(
    character: CharacterModel?,
    comicList: List<ComicModel> = arrayListOf(),
    lazyState: LazyListState,
    padding: PaddingValues,
    isLoadingHeader: Boolean,
    isLoadingList: Boolean,
) {

    LazyColumn(
        modifier = Modifier.padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyState,
    ) {
        item {

            SkeletonLandscapeImage(
                isLoading = isLoadingHeader,
                contentAfterLoading = {
                    character?.let {
                        HeaderDetailsView(it)
                    }
                },
            )
        }

        if(isLoadingList){
            items(10){
                SkeletonRow(
                    imageSize = ThemeConfig.theme.spacing.sizeSpacing200,
                    modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                )
            }
        }else{
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

@Composable
fun HeaderDetailsView(
    character: CharacterModel,
){
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
