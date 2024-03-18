package dev.spikeysanju.wiggles.pagecomponents

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import dev.spikeysanju.wiggles.model.Dog

class DogCard(private val compose: SemanticsNodeInteractionsProvider) {
    private val dogImage = compose.onNodeWithTag("dogImage", true)
    private val dogName = compose.onNodeWithTag("dogName", true)
    private val dogAdditionalInfo = compose.onNodeWithTag("dogAdditionalInfo", true)
    private val dogLocation = compose.onNodeWithTag("dogLocation", true)
    private val genderTag = GenderTagTestComponent(compose)

    fun verifyCard(dog: Dog) {
        dogImage.assertIsDisplayed()
        dogName.assertTextEquals(dog.name)
        dogAdditionalInfo.assertTextEquals("${dog.age}yrs | ${dog.gender}")
        dogLocation.assertTextEquals(dog.location)
        genderTag.verifyGenderTagDisplayed()
    }
}