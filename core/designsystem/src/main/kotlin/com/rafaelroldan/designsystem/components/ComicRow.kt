package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.Image
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
fun ComicRow(
    modifier: Modifier = Modifier,
    comicName: String = "",
    comicDate: Int = 0,
){
    Row {
        Image(
            modifier = modifier.size(45.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )

        Column {
            Text(
                modifier = modifier,
                text = "Name Comic = $comicName"
            )

            Text(
                modifier = modifier,
                text = "Date Comics = $comicDate"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComicRow(){
    Column {
        ComicRow()
    }
}