
package com.urban.androidhomework.sample.features.characters.detail.usecases

import com.urban.androidhomework.sample.core.interactor.UseCase
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterLocation
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterLocation.Params
import javax.inject.Inject

class GetCharacterLocation
@Inject constructor(private val charactersRepository: CharactersRepository) : UseCase<CharacterLocation, Params>() {

    override suspend fun run(params: Params) = charactersRepository.characterLocation(params.id)

    data class Params(val id: Int)
}


