package dev.spikeysanju.wiggles.pages

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.spikeysanju.wiggles.extensions.getText
import dev.spikeysanju.wiggles.pagecomponents.GenderTagTestComponent

class DogDetailsPage(
    compose: SemanticsNodeInteractionsProvider
) {

    private val title = compose.onNodeWithTag("topBarTitle")
    private val backButton = compose.onNodeWithTag("topBarBackButton")

    private val dogDetailsView = compose.onNodeWithTag("dogDetailsView")

    private val image = compose.onNodeWithTag("dogDetailsImage")

    private val dogName = compose.onNodeWithTag("dogInfoName")
    private val dogLocation = compose.onNodeWithTag("dogInfoLocation")
    private val dogDetailsTime = compose.onNodeWithTag("dogInfoTime")
    private val genderTag = GenderTagTestComponent(compose)

    private val dogDescription = compose.onNodeWithTag("dogDescription")

    private val dogShortInfoCard = compose.onNodeWithTag("dogShortInfoCard")

    private val ownerCard = compose.onNodeWithTag("ownerCard")

    private val adoptButton = compose.onNodeWithTag("adoptButton")


    fun dogName() = dogName.getText()

    fun verifyDogDetails() {
        title.assertIsDisplayed()
        backButton.assertIsDisplayed()

        image.assertIsDisplayed()

        dogName.assertIsDisplayed()
        dogLocation.assertIsDisplayed()
        dogDetailsTime.assertIsDisplayed()
        genderTag.verifyGenderTagDisplayed()

        // INFO: Workaround due to performScrollTo not working within LazyColumn
        //       Nicer solution would be to update compose to above 1.3 version to use performScrollToNode
        dogDetailsView.performGesture { swipeUp() }

        dogDescription.assertIsDisplayed()
        dogShortInfoCard.assertIsDisplayed()
        ownerCard.assertIsDisplayed()
        adoptButton.assertIsDisplayed()
    }
}