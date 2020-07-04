
package com.urban.androidhomework.sample.features.characters.detail.model

import com.urban.androidhomework.sample.core.extension.empty

data class CharacterDetails( val id: Int,
                             val name: String,
                             val poster: String,
                             val gender: String,
                             val status: String,
                             val species: String,
                             val created: String,
                             val url: String,
                             val type: String,
                             val locationId: Int) {

    companion object {
        fun empty() = CharacterDetails(0, String.empty(), String.empty(), String.empty(), String.empty(),
                String.empty(), String.empty(), String.empty(), String.empty(), 0)
    }
}





