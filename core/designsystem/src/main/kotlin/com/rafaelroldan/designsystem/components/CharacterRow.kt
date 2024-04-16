package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafaelroldan.designsystem.R

@Composable
fun CharacterRow(
    modifier: Modifier = Modifier,
    characterName: String = "",
    numComics: Int = 0,
    onViewCLickListener: (() -> Unit)? = null
){
    Row(modifier = modifier
        .clickable { onViewCLickListener?.invoke() },
    ) {
        Image(
            modifier = modifier
                .size(45.dp)
            ,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )

        Column {
            Text(
                modifier = modifier,
                text = "Name Character = $characterName"
            )

            Text(
                modifier = modifier,
                text = "Number Comics = $numComics"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterRow(){
    Column {
        CharacterRow()
    }
}