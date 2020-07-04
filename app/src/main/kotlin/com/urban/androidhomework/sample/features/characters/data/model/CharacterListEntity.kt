package com.urban.androidhomework.sample.features.characters.data.model

import android.net.Uri
import com.urban.androidhomework.sample.core.extension.empty
import com.urban.androidhomework.sample.features.characters.list.model.Character

data class CharacterListEntity(val results: List<CharacterData>)

data class CharacterData(
        private val id: Int,
        private val name: String,
        private val gender: String,
        private val image: String,
        private val created: String
) {

    companion object {
        fun empty() = CharacterData(0, String.empty(), String.empty(),
                String.empty(), String.empty())
    }

    fun toCharacter() = Character(id = id, poster = image, name = name)
}

data class LocationUrlEntity(val name: String,
                             val url: String) {
    companion object {
        fun empty() = LocationUrlEntity(String.empty(), String.empty())
    }

    fun getId() = Uri.parse(url).lastPathSegment?.toInt() ?: 0

}
