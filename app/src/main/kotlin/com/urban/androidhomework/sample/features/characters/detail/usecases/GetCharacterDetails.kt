
package com.urban.androidhomework.sample.features.characters.detail.usecases

import com.urban.androidhomework.sample.core.interactor.UseCase
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterDetails.Params
import javax.inject.Inject

class GetCharacterDetails
@Inject constructor(private val charactersRepository: CharactersRepository) : UseCase<CharacterDetails, Params>() {

    override suspend fun run(params: Params) = charactersRepository.characterDetails(params.id)

    data class Params(val id: Int)
}

