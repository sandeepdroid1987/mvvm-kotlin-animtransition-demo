
package com.urban.androidhomework.sample.features.characters.detail.model

import com.urban.androidhomework.sample.core.extension.empty

data class CharacterLocation(val id: Int,
                             val name: String,
                             val type: String,
                             val dimension: String) {

    companion object {
        fun empty() = CharacterLocation(0, String.empty(), String.empty(), String.empty())
    }
}





