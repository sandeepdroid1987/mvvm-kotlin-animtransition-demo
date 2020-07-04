
package com.urban.androidhomework.sample.features.characters.list.view

import android.os.Parcel
import com.urban.androidhomework.sample.core.platform.KParcelable
import com.urban.androidhomework.sample.core.platform.parcelableCreator

data class CharacterView(val id: Int, val poster: String, val name: String) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::CharacterView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString()!!, parcel.readString()!!)

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(poster)
            writeString(name)
        }
    }
}
