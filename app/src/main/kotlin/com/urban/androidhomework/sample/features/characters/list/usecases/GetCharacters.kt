
package com.urban.androidhomework.sample.features.characters.list.usecases

import com.urban.androidhomework.sample.core.interactor.UseCase
import com.urban.androidhomework.sample.core.interactor.UseCase.None
import com.urban.androidhomework.sample.features.characters.data.repository.CharactersRepository
import com.urban.androidhomework.sample.features.characters.list.model.Character
import javax.inject.Inject

class GetCharacters
@Inject constructor(private val charactersRepository: CharactersRepository) : UseCase<List<Character>, None>() {

    override suspend fun run(params: None) = charactersRepository.characters()
}
