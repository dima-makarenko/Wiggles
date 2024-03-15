package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dev.spikeysanju.wiggles.pages.DogDetailsPage
import dev.spikeysanju.wiggles.pages.HomePage
import org.junit.Rule
import org.junit.Test

class DogDetailsScreenTest {

    @OptIn(ExperimentalAnimationApi::class)
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val homePage = HomePage(composeTestRule)
    private val dogDetailsPage = DogDetailsPage(composeTestRule)

    @Test
    fun allDogDetailsElementsDisplayed() {
        homePage.tapDogCard()
        dogDetailsPage.verifyDogDetails()
    }
}
