package dev.spikeysanju.wiggles.pagecomponents

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule

class GenderTagTestComponent<A : Activity?, C : ComponentActivity>(
    testRule: AndroidComposeTestRule<ActivityScenarioRule<A>, C>
) {

    private val genderTag = testRule.onAllNodesWithTag("dogGenderTag", true)

    fun verifyGenderTagDisplayed(index: Int = 0) {
        genderTag[index].assertIsDisplayed()
    }
}