package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rafaelroldan.designsystem.R
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun ErrorView(
    onReload: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(ThemeConfig.theme.spacing.sizeSpacing100),
            tint = ThemeConfig.theme.color.colorFaluRed
        )
        Spacer(modifier = Modifier.size(ThemeConfig.theme.spacing.sizeSpacing8))
        Text(
            text = stringResource(id = R.string.dialog_generic_error),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.size(ThemeConfig.theme.spacing.sizeSpacing40))
        Button(
            modifier = Modifier,
            colors = ButtonColors(
                containerColor = ThemeConfig.theme.color.colorFaluRed,
                contentColor = ThemeConfig.theme.color.colorWhite,
                disabledContainerColor = ThemeConfig.theme.color.colorFaluRed,
                disabledContentColor = ThemeConfig.theme.color.colorWhite
            ),
            onClick = { onReload() }
        ) {
            Text(
                text = stringResource(id = R.string.dialog_retry),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorView(){
    ErrorView {

    }
}