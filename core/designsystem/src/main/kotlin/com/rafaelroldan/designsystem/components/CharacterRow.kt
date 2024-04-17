package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.R
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun CharacterRow(
    modifier: Modifier = Modifier,
    characterName: String = "",
    characterAvatar: String = "",
    numComics: Int = 0,
    onViewCLickListener: (() -> Unit)? = null
){

    Card(
        modifier = modifier
            .testTag(ConstantsTesting.TEST_TAG_CHARACTER_ROW)
            .fillMaxWidth()
            .clickable { onViewCLickListener?.invoke() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = ThemeConfig.theme.spacing.sizeSpacing2
        ),
        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing8),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = characterAvatar,
                placeholder = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier.size(ThemeConfig.theme.spacing.sizeSpacing65)
            )

            Column(
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing8)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = "Name Character = $characterName"
                )

                Text(
                    modifier = Modifier,
                    text = "Number Comics = $numComics"
                )
            }
        }
    }
}

@Preview()
@Composable
fun PreviewCharacterRow(){
    Column {
        CharacterRow()
    }
}
