package dev.spikeysanju.wiggles.pagecomponents

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag

class GenderTagTestComponent(
    compose: SemanticsNodeInteractionsProvider
) {

    private val genderTag = compose.onAllNodesWithTag("dogGenderTag", true)

    fun verifyGenderTagDisplayed(index: Int = 0) {
        genderTag[index].assertIsDisplayed()
    }
}