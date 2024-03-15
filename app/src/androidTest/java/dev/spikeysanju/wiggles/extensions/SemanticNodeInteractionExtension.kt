package dev.spikeysanju.wiggles.extensions

import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.semantics.SemanticsProperties.TestTag
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.test.SemanticsNodeInteraction

fun SemanticsNodeInteraction.getText() =
    this.fetchSemanticsNode().config.getOrNull(Text)?.get(0)?.text
        ?: throw(IllegalArgumentException(
            "Element ${this.fetchSemanticsNode().config.getOrNull(TestTag)} " +
                    "should have non-null text")
                )

fun SemanticsNodeInteractionCollection.getText(index: Int) =
    this[index].getText()
