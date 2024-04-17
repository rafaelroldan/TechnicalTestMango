package com.rafaelroldan.designsystem.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val AppleRed = Color(0xFFff0800)
val Night = Color(0xFF111111)
val FaluRed = Color(0xFF801818)

data class CustomColor (
    val colorRedLight : Color = Color(0xFFff8480),
    val colorAppleRed : Color = Color(0xFFff0800),
    val colorNight : Color = Color(0xFF111111),
    val colorFaluRed : Color = Color(0xFF801818),
    val colorWhite : Color = Color(0xFFFFFFFF),
    val colorGrey : Color = Color(0xFFE0E0E0),
    val colorGrey60 : Color = Color(0x60E0E0E0)
)
