
package com.urban.androidhomework.sample.features.characters.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.urban.androidhomework.sample.core.platform.BaseViewModel
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.view.CharacterDetailsView
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterLocation
import com.urban.androidhomework.sample.features.characters.detail.view.CharacterLocationView
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterDetails
import com.urban.androidhomework.sample.features.characters.detail.usecases.GetCharacterLocation
import com.urban.androidhomework.sample.features.characters.detail.usecases.OpenCharacterInfo
import javax.inject.Inject

class CharacterDetailsViewModel
@Inject constructor(private val getCharacterDetails: GetCharacterDetails,
                    private val getCharacterLocation: GetCharacterLocation,
                    private val openCharacterInfo: OpenCharacterInfo) : BaseViewModel() {

    var characterDetails: MutableLiveData<CharacterDetailsView> = MutableLiveData()

    var characterLocation: MutableLiveData<CharacterLocationView> = MutableLiveData()

    fun loadCharacterDetails(characterId: Int) =
            getCharacterDetails(GetCharacterDetails.Params(characterId)) { it.fold(::handleFailure, ::handleCharacterDetails) }

    fun loadCharacterLocation(locationId: Int) =
            getCharacterLocation(GetCharacterLocation.Params(locationId)) { it.fold(::handleFailure, ::handleCharacterLocation) }

    fun openCharacterInfo(url: String) = openCharacterInfo(OpenCharacterInfo.Params(url))

    private fun handleCharacterDetails(character: CharacterDetails) {
        this.characterDetails.value = CharacterDetailsView(character.id, character.name, character.poster,
                character.gender, character.status, character.species, character.created, character.url, character.type, character.locationId)
    }

    private fun handleCharacterLocation(location: CharacterLocation) {
        this.characterLocation.value = CharacterLocationView(location.id, location.name, location.type, location.dimension)
    }
}