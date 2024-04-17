package com.rafaelroldan.ui

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rafaelroldan.designsystem.components.CharacterRow
import org.junit.Rule
import org.junit.Test

class CharacterRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCharacterRow() {
        composeTestRule.setContent {
            CharacterRow(
                characterName = "MarvelTest",
                characterAvatar = "",
                numComics = 0
            )
        }
        composeTestRule
            .onNodeWithContentDescription("A")
            .assertIsSelected()
    }
}