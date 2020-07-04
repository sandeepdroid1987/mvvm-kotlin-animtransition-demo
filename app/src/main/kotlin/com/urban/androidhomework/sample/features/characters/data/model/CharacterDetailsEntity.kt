
package com.urban.androidhomework.sample.features.characters.data.model

import com.urban.androidhomework.sample.core.extension.empty
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterDetails

data class CharacterDetailsEntity(private val id: Int,
                                  private val name: String,
                                  private val image: String,
                                  private val gender: String,
                                  private val status: String,
                                  private val species: String,
                                  private val created: String,
                                  private val url: String,
                                  private val type: String,
                                  private val location: LocationUrlEntity) {

    companion object {
        fun empty() = CharacterDetailsEntity(0, String.empty(), String.empty(),
                String.empty(), String.empty(), String.empty(), String.empty(), String.empty(), String.empty(), LocationUrlEntity.empty())
    }

    fun toCharacterDetails() = CharacterDetails(id = id, name = name, poster = image, species = species, gender = gender, status = status, locationId = location.getId(), type = type, url = url, created = created)
}
