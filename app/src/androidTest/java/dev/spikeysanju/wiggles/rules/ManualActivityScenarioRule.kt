package dev.spikeysanju.wiggles.rules

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import org.junit.rules.ExternalResource

class ManualActivityScenarioRule(private val activity: Class<out Activity>): ExternalResource() {

    private lateinit var activityScenario: ActivityScenario<out Activity>
    override fun after() {
        activityScenario.close()
    }

    fun launch() {
        activityScenario = ActivityScenario.launch(activity)
    }
}