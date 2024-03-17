package dev.spikeysanju.wiggles.rules

import dev.spikeysanju.wiggles.data.FakeDogDatabase
import dev.spikeysanju.wiggles.model.Dog
import org.junit.rules.ExternalResource

class DatabaseStubRule : ExternalResource() {

    private lateinit var originalDogs: List<Dog>
    override fun before() {
        originalDogs = FakeDogDatabase.dogList
    }

    override fun after() {
        FakeDogDatabase.dogList = originalDogs
    }

    fun setDogsList(dogs: List<Dog>) {
        FakeDogDatabase.dogList = dogs
    }

}