package com.rafaelroldan.designsystem

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.designsystem.components.SearchBarWidget
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchBarWidgetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun beforeSearchBarWidget() {
        composeTestRule.setContent {
            SearchBarWidget(
                modifier = Modifier,
                query = "MarvelTest",
                label = "MarvelTest",
                placeholder = "Description",
                onQueryChanged = {

                }
            )
        }
    }

    @Test
    fun testSearchBarWidgetCanDisplay() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_SEARCHBAR)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchBarWidgetHasClick() {
        composeTestRule
            .onNodeWithTag(ConstantsTesting.TEST_TAG_SEARCHBAR)
            .assertHasClickAction()
    }
}
