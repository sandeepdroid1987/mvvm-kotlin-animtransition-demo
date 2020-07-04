
package com.urban.androidhomework.sample.features.characters.list.model

import com.urban.androidhomework.sample.core.extension.empty

data class Character(val id: Int, val poster: String, val name: String) {

    companion object {
        fun empty() = Character(0, String.empty(), String.empty())
    }
}
