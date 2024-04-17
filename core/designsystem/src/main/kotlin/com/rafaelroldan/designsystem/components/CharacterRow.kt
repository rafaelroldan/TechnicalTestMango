package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

        Box{
            AsyncImage(
                model = characterAvatar,
                placeholder = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing8,
                        end = ThemeConfig.theme.spacing.sizeSpacing20,
                    )
                    .drawBehind {
                        drawCircle(
                            color = ThemeConfig.theme.color.colorFaluRed,
                            radius = (this.size.maxDimension / 1.5).toFloat()
                        )
                    },
                text = numComics.toString(),
                color = ThemeConfig.theme.color.colorWhite
            )

            Column(
                Modifier
                    .background(ThemeConfig.theme.color.colorGrey60)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing4),
                    text = characterName,
                    color = ThemeConfig.theme.color.colorFaluRed,
                    fontFamily = ThemeConfig.theme.font.comicHelvetic,
                    fontWeight = FontWeight.Medium
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
