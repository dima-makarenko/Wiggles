package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.spikeysanju.wiggles.pages.DogDetailsPage
import dev.spikeysanju.wiggles.pages.HomePage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @OptIn(ExperimentalAnimationApi::class)
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val homePage = HomePage(composeTestRule)

    @Test
    fun allTopBarAndDogCardsElementsDisplayed() {
        homePage.verifyTopBar()
        for (i in 0 until homePage.dogsCount()) {
            homePage.verifyDogCard(i)
        }
    }

    @Test
    fun tapDogCardOpensDetails() {
        val dogCardIndex = 0
        val dogDetailsPage = DogDetailsPage(composeTestRule)

        val dogName = homePage.dogName(dogCardIndex)
        homePage.tapDogCard(dogCardIndex)
        val dogDetailsName = dogDetailsPage.dogName()
        assert(dogDetailsName == dogName
        ) { "Incorrect dog details page is opened\nExpected: $dogName\nActual: $dogDetailsName" }
    }
}