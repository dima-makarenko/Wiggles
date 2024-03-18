package dev.spikeysanju.wiggles.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex

class HomePage(private val compose: SemanticsNodeInteractionsProvider) {

    private val title = compose.onNodeWithTag("topBarTitle")
    private val subtitle = compose.onNodeWithTag("topBarSubtitle")
    private val themeSwitch = compose.onNodeWithTag("topBarThemeSwitch")

    private val dogsList = compose.onNodeWithTag("dogsList")
    private val dogCards = compose.onAllNodesWithTag("dogCard")

    fun tapDogCard(index: Int = 0) = dogCards[index].performClick()

    fun verifyTopBar() {
        title.assertIsDisplayed()
        subtitle.assertIsDisplayed()
        themeSwitch.assertIsDisplayed()
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
}