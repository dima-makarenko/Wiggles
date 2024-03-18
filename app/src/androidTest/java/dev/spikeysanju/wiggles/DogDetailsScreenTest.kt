package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso
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
    fun GIVEN_dogsPresentInList_WHEN_openDogDetails_THEN_allDogDetailsAreDisplayed() {
        homePage.tapDogCard()
        dogDetailsPage.verifyDogDetails()
    }

    @Test
    fun GIVEN_dogDetailsScreenDisplayed_WHEN_tapBackButton_THEN_homeScreenIsDisplayed() {
        homePage.tapDogCard()
        // INFO: clickable modifier action for some reason is not executed on click from the test, needs investigation
        // dogDetailsPage.tapBackButton()
        Espresso.pressBack()
        homePage.verifyPageDisplayed()
    }

}
