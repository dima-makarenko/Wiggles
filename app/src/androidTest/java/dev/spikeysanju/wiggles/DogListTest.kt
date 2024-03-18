package dev.spikeysanju.wiggles

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
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
import java.math.RoundingMode
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class DogListTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @OptIn(ExperimentalAnimationApi::class)
    @get:Rule
    val activityRule = ManualActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val databaseRule = DatabaseStubRule()

    private val homePage = HomePage(composeTestRule)

    @Test
    fun GIVEN_dogsPresentInList_WHEN_tapOnDogCard_THEN_correctDogDetailsDisplayed() {
        databaseRule.setDogsList(DOG_LIST)
        activityRule.launch()

        homePage.tapDogCard(0)

        val dogDetailsPage = DogDetailsPage(composeTestRule)
        val actualDogDetailsName = dogDetailsPage.dogName()
        val expectedDogDetailsName = DOG_LIST[0].name
        assert(actualDogDetailsName == expectedDogDetailsName
        ) { "Incorrect dog details page is opened\nExpected: $expectedDogDetailsName" +
                "\nActual: $actualDogDetailsName" }
    }

    @Test
    fun GIVEN_emptyDogList_WHEN_appIsLaunched_THEN_noDogCardsAreDisplayed() {
        databaseRule.setDogsList(EMPTY_DOG_LIST)
        activityRule.launch()
        homePage.verifyEmptyList()
    }

    // INFO: Fails due to bug with dog items duplication. Can be fixed by removing dogList.forEach at Home screen
    @Test
    fun GIVEN_longDogList_WHEN_homeScreenIsScrolledDown_THEN_LastDogCardIsDisplayed() {
        databaseRule.setDogsList(LONG_DOG_LIST)
        activityRule.launch()
        homePage.scrollToDog(LONG_DOG_LIST.size)
        homePage.verifyDogDisplayed(LONG_DOG_LIST.last().name)
    }

    companion object {
        val EMPTY_DOG_LIST = emptyList<Dog>()

        val DOG_LIST = listOf(
            Dog(
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
            ),
            Dog(
                1,
                "Skooby Doo",
                3.5,
                "Male",
                "Gold",
                12.4,
                "412m away",
                R.drawable.blue_dog,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
                FakeDogDatabase.owner
            ),
            Dog(
                2,
                "Miss Agnes",
                3.5,
                "Female",
                "White",
                9.6,
                "879m away",
                R.drawable.red_dog,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
                FakeDogDatabase.owner
            ),
        )

        private val random = generateRandom()
        private fun generateRandom(): Random {
            val seed = Random.nextInt(1000)
            Log.d("TEST DATA", "Random seed from test generation is $seed")
            return Random(seed)
        }
        private fun Double.round(decimals: Int) =
            this.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        val LONG_DOG_LIST = (0..15).map {
            Dog(
                it,
                "Dog $it",
                random.nextInt(from = 1, until = 15).toDouble(),
                listOf("Male", "Female").random(random),
                listOf("Brown", "Grey", "White", "Black").random(random),
                random.nextDouble(from = 1.0, until = 65.0).round(2),
                "${Random.nextDouble(from = 1.0, until = 565.0).round(2)}m away",
                listOf(R.drawable.orange_dog, R.drawable.white_dog, R.drawable.blue_dog).random(random),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
                FakeDogDatabase.owner
            )
        }
    }
}