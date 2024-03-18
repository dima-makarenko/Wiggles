package dev.spikeysanju.wiggles.pages

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
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

    fun tapBackButton() = backButton.performClick()

    fun verifyDogName(name: String) {
        dogName.assertTextEquals(name)
    }

    fun verifyDogDetails() {
        title.assertIsDisplayed()
        backButton.assertIsDisplayed()

        image.assertIsDisplayed()

        dogName.assertIsDisplayed()
        dogLocation.assertIsDisplayed()
        dogDetailsTime.assertIsDisplayed()
        genderTag.verifyGenderTagDisplayed()

        dogDetailsView.performScrollToNode(hasTestTag("adoptButton"))

        dogDescription.assertIsDisplayed()
        dogShortInfoCard.assertIsDisplayed()
        ownerCard.assertIsDisplayed()
        adoptButton.assertIsDisplayed()
    }
}