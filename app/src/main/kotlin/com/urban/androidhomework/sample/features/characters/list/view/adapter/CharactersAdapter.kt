
package com.urban.androidhomework.sample.features.characters.list.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.urban.androidhomework.sample.R
import com.urban.androidhomework.sample.core.extension.inflate
import com.urban.androidhomework.sample.core.extension.loadFromUrl
import com.urban.androidhomework.sample.core.navigation.Navigator
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView
import kotlinx.android.synthetic.main.row_character.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class CharactersAdapter
@Inject constructor() : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    internal var collection: List<CharacterView> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (CharacterView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_character))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(characterView: CharacterView, clickListener: (CharacterView, Navigator.Extras) -> Unit) {
            itemView.characterPoster.loadFromUrl(characterView.poster)
            itemView.characterTitle.text = characterView.name
            itemView.setOnClickListener { clickListener(characterView, Navigator.Extras(itemView.characterPoster)) }
        }
    }
}
