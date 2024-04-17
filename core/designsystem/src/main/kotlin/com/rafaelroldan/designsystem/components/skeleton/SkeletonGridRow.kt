package com.rafaelroldan.designsystem.components.skeleton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun SkeletonGridRow(
    modifier: Modifier = Modifier,
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .shimmerEffect()
            )
    }
}
