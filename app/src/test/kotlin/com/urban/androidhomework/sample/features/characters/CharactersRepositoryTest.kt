
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.UnitTest
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository.Network
import com.urban.androidhomework.sample.core.exception.Failure.NetworkConnection
import com.urban.androidhomework.sample.core.exception.Failure.ServerError
import com.urban.androidhomework.sample.core.extension.empty
import com.urban.androidhomework.sample.core.functional.Either
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.urban.androidhomework.sample.core.platform.NetworkHandler
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.urban.androidhomework.sample.features.characters.data.model.CharacterData
import com.urban.androidhomework.sample.features.characters.data.model.CharacterDetailsEntity
import com.urban.androidhomework.sample.features.characters.data.model.CharacterListEntity
import com.urban.androidhomework.sample.features.characters.data.model.LocationUrlEntity
import com.urban.androidhomework.sample.features.characters.data.remote.impl.CharactersService
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.list.model.Character
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class CharactersRepositoryTest : UnitTest() {

    private lateinit var networkRepository: CharactersRepository.Network

    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var service: CharactersService

    @Mock private lateinit var charactersCall: Call<CharacterListEntity>
    @Mock private lateinit var charactersResponse: Response<CharacterListEntity>
    @Mock private lateinit var characterDetailsCall: Call<CharacterDetailsEntity>
    @Mock private lateinit var characterDetailsResponse: Response<CharacterDetailsEntity>

    @Before fun setUp() {
        networkRepository = Network(networkHandler, service)
    }

    @Test fun `should return empty list by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { charactersResponse.body() }.willReturn(null)
        given { charactersResponse.isSuccessful }.willReturn( true )
        given { charactersCall.execute() }.willReturn(charactersResponse)
        given { service.characters() }.willReturn(charactersCall)

        val characters = networkRepository.characters()

        characters shouldEqual Right(emptyList<Character>())
        verify(service).characters()
    }

    @Test fun `should get character list from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { charactersResponse.body() }.willReturn(CharacterListEntity(listOf(CharacterData.empty())))
        given { charactersResponse.isSuccessful }.willReturn(true)
        given { charactersCall.execute() }.willReturn(charactersResponse)
        given { service.characters() }.willReturn(charactersCall)

        val characters = networkRepository.characters()

        characters shouldEqual Right(listOf(Character.empty()))
        verify(service).characters()
    }

    @Test fun `characters service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val characters = networkRepository.characters()

        characters shouldBeInstanceOf Either::class.java
        characters.isLeft shouldEqual true
        characters.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `characters service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val characters = networkRepository.characters()

        characters shouldBeInstanceOf Either::class.java
        characters.isLeft shouldEqual true
        characters.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `characters service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val characters = networkRepository.characters()

        characters shouldBeInstanceOf Either::class.java
        characters.isLeft shouldEqual true
        characters.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `characters request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val characters = networkRepository.characters()

        characters shouldBeInstanceOf Either::class.java
        characters.isLeft shouldEqual true
        characters.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `should return empty character details by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { characterDetailsResponse.body() }.willReturn(null)
        given { characterDetailsResponse.isSuccessful }.willReturn(true)
        given { characterDetailsCall.execute() }.willReturn(characterDetailsResponse)
        given { service.characterDetails(1) }.willReturn(characterDetailsCall)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldEqual Right(CharacterDetails.empty())
        verify(service).characterDetails(1)
    }

    @Test fun `should get character details from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { characterDetailsResponse.body() }.willReturn(
                CharacterDetailsEntity(id = 8, name = "title", image = String.empty(), gender= String.empty(),
                        status = String.empty(), species = String.empty(), created = String.empty(), url = String.empty(), type = String.empty(), location = LocationUrlEntity.empty()))
        given { characterDetailsResponse.isSuccessful }.willReturn(true)
        given { characterDetailsCall.execute() }.willReturn(characterDetailsResponse)
        given { service.characterDetails(1) }.willReturn(characterDetailsCall)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldEqual Right(CharacterDetails(id = 8, name = "title", poster = String.empty(), gender = String.empty(),
                status = String.empty(), species = String.empty(), type = String.empty(), created = String.empty(), url = String.empty(), locationId = 0))
        verify(service).characterDetails(1)
    }

    @Test fun `character details service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldBeInstanceOf Either::class.java
        characterDetails.isLeft shouldEqual true
        characterDetails.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `character details service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldBeInstanceOf Either::class.java
        characterDetails.isLeft shouldEqual true
        characterDetails.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `character details service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldBeInstanceOf Either::class.java
        characterDetails.isLeft shouldEqual true
        characterDetails.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `character details request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val characterDetails = networkRepository.characterDetails(1)

        characterDetails shouldBeInstanceOf Either::class.java
        characterDetails.isLeft shouldEqual true
        characterDetails.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}