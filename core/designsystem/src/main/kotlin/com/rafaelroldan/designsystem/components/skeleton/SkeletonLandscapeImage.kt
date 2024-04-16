package com.rafaelroldan.designsystem.components.skeleton

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonLandscapeImage(modifier: Modifier = Modifier) {
    val color = animateColorAsState(
        targetValue = if (isSystemInDarkTheme()) Color.DarkGray else Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "skeletonLandscapeImageAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color.value)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSkeletonLandscapeImage(){
    Column {
        SkeletonLandscapeImage()
    }
}
