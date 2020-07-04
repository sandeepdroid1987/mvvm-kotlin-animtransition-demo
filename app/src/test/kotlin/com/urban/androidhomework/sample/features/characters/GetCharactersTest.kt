
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.UnitTest
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.urban.androidhomework.sample.core.interactor.UseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.list.model.Character
import com.urban.androidhomework.sample.features.characters.list.usecases.GetCharacters
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetCharactersTest : UnitTest() {

    private lateinit var getCharacters: GetCharacters

    @Mock private lateinit var charactersRepository: CharactersRepository

    @Before fun setUp() {
        getCharacters = GetCharacters(charactersRepository)
        given { charactersRepository.characters() }.willReturn(Right(listOf(Character.empty())))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getCharacters.run(UseCase.None()) }

        verify(charactersRepository).characters()
        verifyNoMoreInteractions(charactersRepository)
    }
}
