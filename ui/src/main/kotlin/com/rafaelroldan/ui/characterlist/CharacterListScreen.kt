package com.rafaelroldan.ui.characterlist


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.rafaelroldan.designsystem.components.CharacterRow

@Composable
fun CharacterListScreen(
    onCharacterItemClick:(Int) -> Unit,
) {
    Column {
        CharacterRow(){
            onCharacterItemClick.invoke(2)
        }
    }
}