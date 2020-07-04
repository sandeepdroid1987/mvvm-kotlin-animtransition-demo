
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.AndroidTest
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.urban.androidhomework.sample.features.characters.list.model.Character
import com.urban.androidhomework.sample.features.characters.list.viewmodel.CharactersViewModel
import com.urban.androidhomework.sample.features.characters.list.usecases.GetCharacters
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mock

class CharactersViewModelTest : AndroidTest() {

    private lateinit var charactersViewModel: CharactersViewModel

    @Mock private lateinit var getCharacters: GetCharacters

    @Before
    fun setUp() {
        charactersViewModel = CharactersViewModel(getCharacters)
    }

    @Test fun `loading characters should update live data`() {
        val charactersList = listOf(Character(0, "IronMan", "image url"), Character(1, "Batman", "image url"))
        given { runBlocking { getCharacters.run(eq(any())) } }.willReturn(Right(charactersList))

        charactersViewModel.characters.observeForever {
            it!!.size shouldEqualTo 2
            it[0].id shouldEqualTo 0
            it[0].poster shouldEqualTo "IronMan"
            it[1].id shouldEqualTo 1
            it[1].poster shouldEqualTo "Batman"
        }

        runBlocking { charactersViewModel.loadCharacters() }
    }
}