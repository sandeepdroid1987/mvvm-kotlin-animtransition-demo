
package com.urban.androidhomework.sample.features.characters.data.remote.impl

import com.urban.androidhomework.sample.features.characters.data.remote.api.CharactersApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersService
@Inject constructor(retrofit: Retrofit) : CharactersApi {
    private val charactersApi by lazy { retrofit.create(CharactersApi::class.java) }

    override fun characters() = charactersApi.characters()
    override fun characterDetails(characterId: Int) = charactersApi.characterDetails(characterId)
    override fun characterLocation(locationId: Int) = charactersApi.characterLocation(locationId)
}
