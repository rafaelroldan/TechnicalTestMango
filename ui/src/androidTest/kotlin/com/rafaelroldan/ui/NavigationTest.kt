package com.rafaelroldan.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rafaelroldan.ui.navigation.Navigation
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun rallyNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            Navigation()
        }
    }

    @Test
    fun navigationNavHost_verifyOverviewStartDestination() {
        composeTestRule.setContent {
            composeTestRule
                .onNodeWithContentDescription("Overview Screen")
                .assertIsDisplayed()
        }
    }
}