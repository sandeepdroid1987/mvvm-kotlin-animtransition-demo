
package com.urban.androidhomework.sample.features.characters.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.urban.androidhomework.sample.core.interactor.UseCase.None
import com.urban.androidhomework.sample.core.platform.BaseViewModel
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView
import com.urban.androidhomework.sample.features.characters.list.usecases.GetCharacters
import com.urban.androidhomework.sample.features.characters.list.model.Character
import javax.inject.Inject

class CharactersViewModel
@Inject constructor(private val getCharacters: GetCharacters) : BaseViewModel() {

    var characters: MutableLiveData<List<CharacterView>> = MutableLiveData()

    fun loadCharacters() = getCharacters(None()) { it.fold(::handleFailure, ::handleCharacterList) }

    private fun handleCharacterList(characters: List<Character>) {
        this.characters.value = characters.map { CharacterView(it.id, it.poster, it.name) }
    }
}