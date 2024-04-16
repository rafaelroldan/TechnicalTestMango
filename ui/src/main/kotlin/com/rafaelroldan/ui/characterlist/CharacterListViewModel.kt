package com.rafaelroldan.ui.characterlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.usecase.character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase
) : ViewModel() {

    var characterList by mutableStateOf<List<CharacterModel>>(mutableListOf())

    private val _characterListResult = MutableSharedFlow<CharacterListResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val characterListResult: SharedFlow<CharacterListResult> = _characterListResult

    private val offset = mutableIntStateOf(0)
    private val limit = mutableIntStateOf(20)

    init {
        getCharacter()
    }

    private fun getCharacter(){
        viewModelScope.launch {
            _characterListResult.emit(CharacterListResult.Loading)
            characterUseCase.getAllCharacter(
                offset = offset.intValue,
                limit = limit.intValue
            ).collect{
                if(it.error){
                    _characterListResult.emit(CharacterListResult.Error)
                } else {
                    characterList = it.data?.results ?: arrayListOf()
                    _characterListResult.emit(CharacterListResult.Success)
                }
            }

        }
    }
}

sealed interface CharacterListResult {
    data object Loading : CharacterListResult
    data object Success : CharacterListResult
    data object Error : CharacterListResult
}
