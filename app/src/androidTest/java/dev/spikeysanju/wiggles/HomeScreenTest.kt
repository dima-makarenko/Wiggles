package dev.spikeysanju.wiggles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.spikeysanju.wiggles.data.FakeDogDatabase
import dev.spikeysanju.wiggles.model.Dog
import dev.spikeysanju.wiggles.pages.DogDetailsPage
import dev.spikeysanju.wiggles.pages.HomePage
import dev.spikeysanju.wiggles.rules.DatabaseStubRule
import dev.spikeysanju.wiggles.rules.ManualActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @get:Rule
    val activityRule = ManualActivityScenarioRule()

    @get:Rule
    val databaseRule = DatabaseStubRule()

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
        activityRule.launch()
        val dogCardIndex = 0
        val dogDetailsPage = DogDetailsPage(composeTestRule)

        val dogName = homePage.dogName(dogCardIndex)
        homePage.tapDogCard(dogCardIndex)
        val dogDetailsName = dogDetailsPage.dogName()
        assert(dogDetailsName == dogName
        ) { "Incorrect dog details page is opened\nExpected: $dogName\nActual: $dogDetailsName" }
    }

    @Test
    fun emptyList() {
        databaseRule.setDogsList(EMPTY_DOG_LIST)
        activityRule.launch()
        homePage.verifyEmptyList()
    }

    @Test
    fun longList() {
        databaseRule.setDogsList(LONG_DOG_LIST)
        activityRule.launch()
        homePage.scrollToDog(LONG_DOG_LIST.size)
        homePage.verifyDogDisplayed(LONG_DOG_LIST.last().name)
    }

    companion object {
        val EMPTY_DOG_LIST = emptyList<Dog>()

        val LONG_DOG_LIST = (0..15).map {
            Dog(
                it,
                "Dog $it",
                Random.nextDouble(from = 1.0, until = 15.0),
                listOf("Male", "Female").random(),
                listOf("Brown", "Grey", "White", "Black").random(),
                Random.nextDouble(from = 1.0, until = 65.0),
                "${Random.nextDouble(from = 1.0, until = 565.0)}m away",
                listOf(R.drawable.orange_dog, R.drawable.white_dog, R.drawable.blue_dog).random(),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
                FakeDogDatabase.owner
            )
        }
    }
}