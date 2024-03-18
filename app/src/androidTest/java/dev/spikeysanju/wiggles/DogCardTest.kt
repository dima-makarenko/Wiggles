package dev.spikeysanju.wiggles

import androidx.compose.ui.test.junit4.createComposeRule
import dev.spikeysanju.wiggles.component.ItemDogCard
import dev.spikeysanju.wiggles.data.FakeDogDatabase
import dev.spikeysanju.wiggles.model.Dog
import dev.spikeysanju.wiggles.pagecomponents.DogCard
import org.junit.Rule
import org.junit.Test

class DogCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dogCard = DogCard(composeTestRule)

    @Test
    fun WHEN_dogCardHasAllInfo_THEN_correctDogInfoIsDisplayed() {
        val dog = Dog(
            0,
            "Hachiko",
            3.5,
            "Male",
            "Brown",
            12.9,
            "389m away",
            R.drawable.orange_dog,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
            FakeDogDatabase.owner
        )

        composeTestRule.setContent { ItemDogCard(dog) {} }
        dogCard.verifyCard(dog)
    }
}