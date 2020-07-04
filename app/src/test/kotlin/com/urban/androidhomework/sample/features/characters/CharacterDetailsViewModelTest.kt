
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.AndroidTest
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterLocation
import com.urban.androidhomework.sample.features.characters.detail.usecases.OpenCharacterInfo
import com.urban.androidhomework.sample.features.characters.detail.viewmodel.CharacterDetailsViewModel
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class CharacterDetailsViewModelTest : AndroidTest() {

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @Mock private lateinit var getCharacterDetails: GetCharacterDetails
    @Mock private lateinit var openCharacterInfo: OpenCharacterInfo
    @Mock private lateinit var getCharacterLocation: GetCharacterLocation

    @Before
    fun setUp() {
        characterDetailsViewModel = CharacterDetailsViewModel(getCharacterDetails, getCharacterLocation, openCharacterInfo)
    }

    @Test fun `loading character details should update live data`() {
        val characterDetails = CharacterDetails(0, "IronMan", "poster", "gender",
                "alive", "human", "2020", "url", "type", 1)
        given { runBlocking { getCharacterDetails.run(eq(any())) } }.willReturn(Right(characterDetails))

        characterDetailsViewModel.characterDetails.observeForever {
            with(it!!) {
                id shouldEqualTo 0
                name shouldEqualTo "IronMan"
                image shouldEqualTo "poster"
                gender shouldEqualTo "gender"
                status shouldEqualTo "alive"
                species shouldEqualTo "human"
                created shouldEqualTo "2020"
                url shouldEqualTo "url"
                type shouldEqualTo "type"
                locationId shouldEqualTo 1
            }
        }

        runBlocking { characterDetailsViewModel.loadCharacterDetails(0) }
    }
}