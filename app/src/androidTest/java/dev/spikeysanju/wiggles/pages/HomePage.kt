package dev.spikeysanju.wiggles.pages

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import dev.spikeysanju.wiggles.extensions.getText
import dev.spikeysanju.wiggles.pagecomponents.GenderTagTestComponent
import kotlin.AssertionError

class HomePage(
    private val compose: SemanticsNodeInteractionsProvider
) {

    private val title = compose.onNodeWithTag("topBarTitle")
    private val subtitle = compose.onNodeWithTag("topBarSubtitle")

    private val dogsList = compose.onNodeWithTag("dogsList")
    private val dogCards = compose.onAllNodesWithTag("dogCard")
    private val dogImages = compose.onAllNodesWithTag("dogImage", true)
    private val dogNames = compose.onAllNodesWithTag("dogName", true)
    private val dogAdditionalInfos = compose.onAllNodesWithTag("dogAdditionalInfo", true)
    private val dogLocations = compose.onAllNodesWithTag("dogLocation", true)
    private val genderTag = GenderTagTestComponent(compose)

    fun tapDogCard(index: Int = 0) = dogCards[index].performClick()
    fun dogName(index: Int = 0) = dogNames.getText(index)
    fun dogsCount() = dogCards.fetchSemanticsNodes().size

    fun verifyTopBar() {
        title.assertIsDisplayed()
        subtitle.assertIsDisplayed()
    }

    fun verifyEmptyList() {
        dogCards.fetchSemanticsNodes().forEachIndexed { index, _ ->
            dogCards[index].assertIsNotDisplayed()
        }
    }

    fun verifyDogDisplayed(name: String) {
        compose.onNodeWithText(name).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun scrollToDog(index: Int) {
        dogsList.performScrollToIndex(index)
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