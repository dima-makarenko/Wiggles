package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dev.spikeysanju.wiggles.pages.DogDetailsPage
import dev.spikeysanju.wiggles.pages.HomePage
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalAnimationApi::class)
class DogDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val homePage = HomePage(composeTestRule)
    private val dogDetailsPage = DogDetailsPage(composeTestRule)

    @Test
    fun GIVEN_dogsPresentInList_WHEN_openDogDetails_THEN_allDogDetailsAreDisplayed() {
        homePage.tapDogCard()
        dogDetailsPage.verifyDogDetails()
    }

    @Test
    fun GIVEN_dogDetailsScreenDisplayed_WHEN_tapBackButton_THEN_homeScreenIsDisplayed() {
        homePage.tapDogCard()
        dogDetailsPage.tapBackButton()
        homePage.verifyPageDisplayed()
    }

}
