package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dev.spikeysanju.wiggles.pages.HomePage
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalAnimationApi::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val homePage = HomePage(composeTestRule)

    @Test
    fun WHEN_launchApp_WHEN_allTopBarElementsAreDisplayed() {
        homePage.verifyTopBar()
    }
}