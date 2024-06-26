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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.R
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun ComicRow(
    modifier: Modifier = Modifier,
    name: String = "",
    avatar: String = "",
    description: String = "",
    onViewCLickListener: (() -> Unit)? = null
){

    Card(
        modifier = modifier
            .testTag(ConstantsTesting.TEST_TAG_COMIC_ROW)
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
                model = avatar,
                placeholder = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier
                    .size(ThemeConfig.theme.spacing.sizeSpacing200)
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing4),
            )

            Column(
                Modifier
                    .padding(end = ThemeConfig.theme.spacing.sizeSpacing8)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = name,
                    fontFamily = ThemeConfig.theme.font.comicHelvetic,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    modifier = Modifier.padding(top = ThemeConfig.theme.spacing.sizeSpacing20),
                    text = description,
                    fontFamily = ThemeConfig.theme.font.comicHelvetic,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview()
@Composable
fun PreviewComicRow(){
    Column {
        ComicRow()
    }
}
