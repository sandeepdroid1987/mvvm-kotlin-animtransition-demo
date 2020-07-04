
package com.urban.androidhomework.sample.features.characters.data.repository

import com.urban.androidhomework.sample.core.exception.Failure
import com.urban.androidhomework.sample.core.exception.Failure.NetworkConnection
import com.urban.androidhomework.sample.core.exception.Failure.ServerError
import com.urban.androidhomework.sample.core.functional.Either
import com.urban.androidhomework.sample.core.functional.Either.Left
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.urban.androidhomework.sample.core.platform.NetworkHandler
import com.urban.androidhomework.sample.features.characters.data.model.CharacterDetailsEntity
import com.urban.androidhomework.sample.features.characters.data.model.CharacterListEntity
import com.urban.androidhomework.sample.features.characters.data.model.CharacterLocationEntity
import com.urban.androidhomework.sample.features.characters.data.remote.impl.CharactersService
import com.urban.androidhomework.sample.features.characters.list.model.Character
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterLocation
import retrofit2.Call
import javax.inject.Inject

interface CharactersRepository {
    fun characters(): Either<Failure, List<Character>>
    fun characterDetails(characterId: Int): Either<Failure, CharacterDetails>
    fun characterLocation(locationId: Int): Either<Failure, CharacterLocation>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: CharactersService) : CharactersRepository {

        override fun characters(): Either<Failure, List<Character>> {
            return when (networkHandler.isConnected) {
                true -> request(service.characters(), { it.results.map { characterEntity -> characterEntity.toCharacter() } }, CharacterListEntity(emptyList()))
                false, null -> Left(NetworkConnection)
            }
        }

        override fun characterDetails(characterId: Int): Either<Failure, CharacterDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.characterDetails(characterId), { it.toCharacterDetails() }, CharacterDetailsEntity.empty())
                false, null -> Left(NetworkConnection)
            }
        }

        override fun characterLocation(locationId: Int): Either<Failure, CharacterLocation> {
            return when (networkHandler.isConnected) {
                true -> request(service.characterLocation(locationId), { it.toCharacterLocation() }, CharacterLocationEntity.empty())
                false, null -> Left(NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}
