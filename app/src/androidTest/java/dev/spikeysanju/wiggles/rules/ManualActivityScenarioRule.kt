package dev.spikeysanju.wiggles.rules

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.test.core.app.ActivityScenario
import dev.spikeysanju.wiggles.MainActivity
import org.junit.rules.ExternalResource

@OptIn(ExperimentalAnimationApi::class)
class ManualActivityScenarioRule: ExternalResource() {

    private var activityScenario: ActivityScenario<MainActivity>? = null
    override fun after() {
        activityScenario?.close()
    }

    fun launch() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }
}