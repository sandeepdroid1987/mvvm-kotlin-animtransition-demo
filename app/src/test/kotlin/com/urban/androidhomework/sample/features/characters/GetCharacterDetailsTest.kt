
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.UnitTest
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterDetails
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetCharacterDetailsTest : UnitTest() {

    private val CHARACTER_ID = 1

    private lateinit var getCharacterDetails: GetCharacterDetails

    @Mock private lateinit var charactersRepository: CharactersRepository

    @Before fun setUp() {
        getCharacterDetails = GetCharacterDetails(charactersRepository)
        given { charactersRepository.characterDetails(CHARACTER_ID) }.willReturn(Right(CharacterDetails.empty()))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getCharacterDetails.run(GetCharacterDetails.Params(CHARACTER_ID)) }

        verify(charactersRepository).characterDetails(CHARACTER_ID)
        verifyNoMoreInteractions(charactersRepository)
    }
}
