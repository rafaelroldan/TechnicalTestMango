package com.rafaelroldan.ui.characterdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelroldan.mappers.MarvelResult
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

    private val _characterResult = MutableSharedFlow<StateDetailsResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val characterResult: SharedFlow<StateDetailsResult> = _characterResult

    var comicsList by mutableStateOf<List<ComicModel>>(mutableListOf())

    private val _comicsListResult = MutableSharedFlow<StateDetailsResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val comicsListResult: SharedFlow<StateDetailsResult> = _comicsListResult

    private val _isErrorShowing = MutableSharedFlow<StateDetailsResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val isErrorShowing: SharedFlow<StateDetailsResult> = _isErrorShowing

    fun getCharacter(characterId: Int){
        viewModelScope.launch {
            _isErrorShowing.emit(StateDetailsResult.Loading)
            _characterResult.emit(StateDetailsResult.Loading)
            characterUseCase.getCharacterById(
                characterId
            ).collect{
                if(it is MarvelResult.Success){
                    character = it.data.firstOrNull()
                    getComics(characterId)
                    _characterResult.emit(StateDetailsResult.Success)
                } else {
                    _isErrorShowing.emit(StateDetailsResult.Error)
                    _characterResult.emit(StateDetailsResult.Error)
                }
            }

        }
    }

    private fun getComics(characterId: Int){
        viewModelScope.launch {
            _comicsListResult.emit(StateDetailsResult.Loading)
            comicUseCase.getComicByCharacter(
                characterId
            ).collect{
                if(it is MarvelResult.Success){
                    comicsList = it.data
                    _comicsListResult.emit(StateDetailsResult.Success)
                } else {
                    _isErrorShowing.emit(StateDetailsResult.Error)
                    _comicsListResult.emit(StateDetailsResult.Error)
                }
            }

        }
    }

    fun retry(characterId: Int){
        getCharacter(characterId)
    }
}

sealed interface StateDetailsResult {
    data object Loading : StateDetailsResult
    data object Success : StateDetailsResult
    data object Error : StateDetailsResult
}
