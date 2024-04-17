package com.rafaelroldan.technicaltestmango

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelroldan.common.ConstantsTesting
import com.rafaelroldan.ui.MainActivity
import com.rafaelroldan.ui.navigation.Navigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var navController: TestNavHostController

    @Before
    fun navigationNavHost() {

        hiltRule.inject()

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            Navigation()

            composeTestRule
                .onNodeWithTag(ConstantsTesting.TEST_TAG_NAVIGATION_HOST)
                .assertIsDisplayed()
        }
    }

    @Test
    fun navigationNavHost_verifyOverviewStartDestination() {

        composeTestRule.activity.setContent {
            composeTestRule
                .onNodeWithTag(ConstantsTesting.TEST_TAG_NAVIGATION_HOST)
                .assertIsDisplayed()
        }
    }
}