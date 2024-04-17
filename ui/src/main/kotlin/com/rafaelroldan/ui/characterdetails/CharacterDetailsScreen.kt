package com.rafaelroldan.ui.characterdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafaelroldan.designsystem.components.ComicRow
import com.rafaelroldan.designsystem.components.LandscapeImage
import com.rafaelroldan.designsystem.components.skeleton.SkeletonLandscapeImage
import com.rafaelroldan.designsystem.components.skeleton.SkeletonRow
import com.rafaelroldan.designsystem.theme.ThemeConfig
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.designsystem.R
import java.util.Locale

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
                navigationIcon = {
                    IconButton(onClick = { onBackNavigation.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
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
                modifier = Modifier.padding(bottom = ThemeConfig.theme.spacing.sizeSpacing20),
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

            if(comicList.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = ThemeConfig.theme.spacing.sizeSpacing2
                        ),
                        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing8),
                    ) {

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    top = ThemeConfig.theme.spacing.sizeSpacing20,
                                    start = ThemeConfig.theme.spacing.sizeSpacing20,
                                    end = ThemeConfig.theme.spacing.sizeSpacing20,
                                    bottom = ThemeConfig.theme.spacing.sizeSpacing8,
                                ),
                            text = stringResource(R.string.character_details_related_comics),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontFamily = ThemeConfig.theme.font.comicHelvetic,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
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

@Composable
fun HeaderDetailsView(
    character: CharacterModel,
){
    LandscapeImage(
        modifier = Modifier.padding(bottom = ThemeConfig.theme.spacing.sizeSpacing20),
        image = character.avatar
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = ThemeConfig.theme.spacing.sizeSpacing20,
                end = ThemeConfig.theme.spacing.sizeSpacing20,
                bottom = ThemeConfig.theme.spacing.sizeSpacing20,
            ),
        text = character.name.uppercase(Locale.getDefault()),
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        lineHeight = 36.sp,
        fontFamily = ThemeConfig.theme.font.heroesLegend,
    )

    if(character.description.isNotEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = ThemeConfig.theme.spacing.sizeSpacing20,
                    end = ThemeConfig.theme.spacing.sizeSpacing20,
                    bottom = ThemeConfig.theme.spacing.sizeSpacing20,
                ),
            text = character.description,
            textAlign = TextAlign.Center,
            fontFamily = ThemeConfig.theme.font.comicHelvetic,
            fontWeight = FontWeight.Medium
        )
    }
}
