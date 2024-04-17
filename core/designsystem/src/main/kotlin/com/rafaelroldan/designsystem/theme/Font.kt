package com.rafaelroldan.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.rafaelroldan.designsystem.R

class Font {
    val actionsComic: FontFamily = FontFamily(
        Font(R.font.action_comics),
    )

    val comicHelvetic: FontFamily = FontFamily(
        Font(R.font.comichelvetic_medium, FontWeight.Medium),
    )

    val heroesLegend: FontFamily = FontFamily(
        Font(R.font.heroes_legend),
    )
}