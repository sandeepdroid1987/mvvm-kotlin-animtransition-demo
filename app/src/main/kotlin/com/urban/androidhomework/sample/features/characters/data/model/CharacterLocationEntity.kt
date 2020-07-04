
package com.urban.androidhomework.sample.features.characters.data.model

import com.urban.androidhomework.sample.core.extension.empty
import com.urban.androidhomework.sample.features.characters.detail.model.CharacterLocation

data class CharacterLocationEntity(private val id: Int,
                                   private val name: String,
                                   private val type: String,
                                   private val dimension: String) {

    companion object {
        fun empty() = CharacterLocationEntity(0, String.empty(), String.empty(), String.empty())
    }

    fun toCharacterLocation() = CharacterLocation(id = id, name = name, type = type, dimension = dimension)
}
