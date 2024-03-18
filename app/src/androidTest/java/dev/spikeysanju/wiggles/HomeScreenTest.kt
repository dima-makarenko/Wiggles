package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dev.spikeysanju.wiggles.pages.HomePage
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @OptIn(ExperimentalAnimationApi::class)
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val homePage = HomePage(composeTestRule)

    @Test
    fun GIVEN_dogsPresentInList_WHEN_appLaunched_WHEN_allTopBarElementsAreDisplayed() {
        homePage.verifyTopBar()
    }
}