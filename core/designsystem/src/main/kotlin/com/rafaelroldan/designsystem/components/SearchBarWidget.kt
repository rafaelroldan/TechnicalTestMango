package com.rafaelroldan.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.theme.ThemeConfig

@Composable
fun SearchBarWidget(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    label: String? = null,
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(ThemeConfig.theme.spacing.sizeSpacing8),
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChanged(it)
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onQueryChanged("")
                    }
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            },
            placeholder = if (placeholder != null) {
                {
                    Text(placeholder)
                }
            } else {
                null
            },
            label = if (label != null) {
                {
                    Text(label)
                }
            } else {
                null
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                },
            ),
            modifier = Modifier
                .testTag(ConstantsTesting.TEST_TAG_SEARCHBAR)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = ThemeConfig.theme.color.colorGrey,
                unfocusedTextColor = ThemeConfig.theme.color.colorGrey,
                focusedBorderColor = ThemeConfig.theme.color.colorGrey,
                unfocusedBorderColor = ThemeConfig.theme.color.colorGrey,
                cursorColor = ThemeConfig.theme.color.colorGrey,
                focusedLabelColor = ThemeConfig.theme.color.colorGrey,
                unfocusedLabelColor = ThemeConfig.theme.color.colorGrey,
            )
        )
    }
}
