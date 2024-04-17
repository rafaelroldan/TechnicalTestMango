package com.rafaelroldan.designsystem

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.components.ComicRow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun beforeComicRow() {
        composeTestRule.setContent {
            ComicRow(
                name = "MarvelTest",
                avatar = "",
                description = "Description"
            ){

            }
        }
    }

    @Test
    fun testComicRowCanDisplay() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_COMIC_ROW)
            .assertIsDisplayed()
    }

    @Test
    fun testComicRowHasClick() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_COMIC_ROW)
            .assertHasClickAction()
    }
}