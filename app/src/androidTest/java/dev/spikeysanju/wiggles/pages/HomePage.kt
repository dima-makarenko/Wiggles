package dev.spikeysanju.wiggles.pages

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.spikeysanju.wiggles.extensions.getText
import dev.spikeysanju.wiggles.pagecomponents.GenderTagTestComponent
import kotlin.AssertionError

class HomePage<A : Activity?, C : ComponentActivity>(
    testRule: AndroidComposeTestRule<ActivityScenarioRule<A>, C>
) {

    private val title = testRule.onNodeWithTag("topBarTitle")
    private val subtitle = testRule.onNodeWithTag("topBarSubtitle")

    private val dogsList = testRule.onNodeWithTag("dogsList")
    private val dogCards = testRule.onAllNodesWithTag("dogCard")
    private val dogImages = testRule.onAllNodesWithTag("dogImage", true)
    private val dogNames = testRule.onAllNodesWithTag("dogName", true)
    private val dogAdditionalInfos = testRule.onAllNodesWithTag("dogAdditionalInfo", true)
    private val dogLocations = testRule.onAllNodesWithTag("dogLocation", true)
    private val genderTag = GenderTagTestComponent(testRule)

    fun tapDogCard(index: Int = 0) = dogCards[index].performClick()
    fun dogName(index: Int = 0) = dogNames.getText(index)
    fun dogsCount() = dogNames.fetchSemanticsNodes().size

    fun verifyTopBar() {
        title.assertIsDisplayed()
        subtitle.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun verifyDogCard(index: Int) {
        dogsList.performScrollToIndex(index + 1)
        try {
            dogNames[index].assertIsDisplayed()
            dogImages[index].assertIsDisplayed()
            dogAdditionalInfos[index].assertIsDisplayed()
            dogLocations[index].assertIsDisplayed()
            genderTag.verifyGenderTagDisplayed(index)
        } catch (e: AssertionError) {
            throw AssertionError("${e.message}. Dog Card index is $index")
        }
    }
}