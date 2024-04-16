package com.rafaelroldan.designsystem.components.skeleton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun SkeletonLandscapeImage(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
) {

    if(isLoading) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .height(ThemeConfig.theme.spacing.sizeSpacing450)
                    .aspectRatio(1f)
                    .shimmerEffect()
            )

            Text(
                text = "",
                modifier = Modifier
                    .height(ThemeConfig.theme.spacing.sizeSpacing40)
                    .fillMaxWidth()
                    .padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing20,
                        start = ThemeConfig.theme.spacing.sizeSpacing100,
                        end = ThemeConfig.theme.spacing.sizeSpacing100,
                    )
                    .shimmerEffect()
            )

            Text(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ThemeConfig.theme.spacing.sizeSpacing100)
                    .padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing20,
                        bottom = ThemeConfig.theme.spacing.sizeSpacing20,
                        start = ThemeConfig.theme.spacing.sizeSpacing20,
                        end = ThemeConfig.theme.spacing.sizeSpacing20,
                    ).shimmerEffect()
            )
        }
    } else {
        contentAfterLoading()
    }
}
