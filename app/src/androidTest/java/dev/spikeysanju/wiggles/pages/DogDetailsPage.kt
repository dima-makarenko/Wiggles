package dev.spikeysanju.wiggles.pages

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.spikeysanju.wiggles.extensions.getText
import dev.spikeysanju.wiggles.pagecomponents.GenderTagTestComponent

class DogDetailsPage<A : Activity?, C : ComponentActivity>(
    testRule: AndroidComposeTestRule<ActivityScenarioRule<A>, C>
) {

    private val title = testRule.onNodeWithTag("topBarTitle")
    private val backButton = testRule.onNodeWithTag("topBarBackButton")

    private val dogDetailsView = testRule.onNodeWithTag("dogDetailsView")

    private val image = testRule.onNodeWithTag("dogDetailsImage")

    private val dogName = testRule.onNodeWithTag("dogInfoName")
    private val dogLocation = testRule.onNodeWithTag("dogInfoLocation")
    private val dogDetailsTime = testRule.onNodeWithTag("dogInfoTime")
    private val genderTag = GenderTagTestComponent(testRule)

    private val dogDescription = testRule.onNodeWithTag("dogDescription")

    private val dogShortInfoCard = testRule.onNodeWithTag("dogShortInfoCard")

    private val ownerCard = testRule.onNodeWithTag("ownerCard")

    private val adoptButton = testRule.onNodeWithTag("adoptButton")


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