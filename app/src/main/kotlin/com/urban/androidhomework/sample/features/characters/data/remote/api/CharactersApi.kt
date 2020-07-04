
package com.urban.androidhomework.sample.features.characters.data.remote.api

import com.urban.androidhomework.sample.features.characters.data.model.CharacterDetailsEntity
import com.urban.androidhomework.sample.features.characters.data.model.CharacterListEntity
import com.urban.androidhomework.sample.features.characters.data.model.CharacterLocationEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CharactersApi {
    companion object {
        private const val PARAM_CHARACTER_ID = "characterId"
        private const val PARAM_CHARACTER_LOCATION_ID = "locationId"

        private const val CHARACTERS = "character"
        private const val CHARACTER_DETAILS = "character/{$PARAM_CHARACTER_ID}"
        private const val CHARACTER_LOCATION = "location/{$PARAM_CHARACTER_LOCATION_ID}"
    }

    @GET(CHARACTERS) fun characters(): Call<CharacterListEntity>
    @GET(CHARACTER_DETAILS) fun characterDetails(@Path(PARAM_CHARACTER_ID) characterId: Int): Call<CharacterDetailsEntity>
    @GET(CHARACTER_LOCATION) fun characterLocation(@Path(PARAM_CHARACTER_LOCATION_ID) locationId: Int): Call<CharacterLocationEntity>

}
