package com.rafaelroldan.designsystem


import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.components.CharacterRow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun beforeCharacterRow() {
        composeTestRule.setContent {
            CharacterRow(
                characterName = "MarvelTest",
                characterAvatar = "",
                numComics = 0
            ) {

            }
        }
    }

    @Test
    fun testCharacterRowCanDisplay() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_CHARACTER_ROW)
            .assertIsDisplayed()
    }

    @Test
    fun testCharacterRowHasClick() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_CHARACTER_ROW)
            .assertHasClickAction()
    }
}
