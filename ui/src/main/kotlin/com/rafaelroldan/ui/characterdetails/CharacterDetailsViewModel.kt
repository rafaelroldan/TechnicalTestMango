package com.rafaelroldan.ui.characterdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.usecase.character.GetCharacterUseCase
import com.rafaelroldan.usecase.comic.GetComicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase,
    private val comicUseCase: GetComicUseCase
) : ViewModel() {

    var character by mutableStateOf<CharacterModel?>(null)

    private val _characterResult = MutableSharedFlow<CharacterDetailsResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val characterResult: SharedFlow<CharacterDetailsResult> = _characterResult

    var comicsList by mutableStateOf<List<ComicModel>>(mutableListOf())

    private val _comicsListResult = MutableSharedFlow<ComicListResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val comicsListResult: SharedFlow<ComicListResult> = _comicsListResult

    fun getCharacter(characterId: Int){
        viewModelScope.launch {
            _characterResult.emit(CharacterDetailsResult.Loading)
            characterUseCase.getCharacterById(
                characterId
            ).collect{
                if(it.error){
                    _characterResult.emit(CharacterDetailsResult.Error)
                } else {
                    character = it.data?.results?.firstOrNull()
                    getComics(characterId)
                    _characterResult.emit(CharacterDetailsResult.Success)
                }
            }

        }
    }

    private fun getComics(characterId: Int){
        viewModelScope.launch {
            _comicsListResult.emit(ComicListResult.Loading)
            comicUseCase.getComicByCharacter(
                characterId
            ).collect{
                if(it.error){
                    _comicsListResult.emit(ComicListResult.Error)
                } else {
                    comicsList = it.data?.results ?: arrayListOf()
                    _comicsListResult.emit(ComicListResult.Success)
                }
            }

        }
    }
}

sealed interface CharacterDetailsResult {
    data object Loading : CharacterDetailsResult
    data object Success : CharacterDetailsResult
    data object Error : CharacterDetailsResult
}

sealed interface ComicListResult {
    data object Loading : ComicListResult
    data object Success : ComicListResult
    data object Error : ComicListResult
}
