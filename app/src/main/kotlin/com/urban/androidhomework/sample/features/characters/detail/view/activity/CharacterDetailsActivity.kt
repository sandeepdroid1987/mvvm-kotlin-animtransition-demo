
package com.urban.androidhomework.sample.features.characters.detail.view.activity

import android.content.Context
import android.content.Intent
import com.urban.androidhomework.sample.core.platform.BaseActivity
import com.urban.androidhomework.sample.core.platform.BaseFragment
import com.urban.androidhomework.sample.features.characters.detail.view.fragment.CharacterDetailsFragment
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView

class CharacterDetailsActivity : BaseActivity() {

    companion object {

        private const val DEEP_LINK_QUERY_CHARACTER_ID = "id"
        private const val INTENT_EXTRA_PARAM_CHARACTER = "com.urban.androidhomework.INTENT_PARAM_CHARACTER"

        fun callingIntent(context: Context, character: CharacterView): Intent {
            val intent = Intent(context, CharacterDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_CHARACTER, character)
            return intent
        }
    }

    override fun fragment() : BaseFragment {
        //handle deeplink
        val id = intent.data?.getQueryParameter(DEEP_LINK_QUERY_CHARACTER_ID)

        val characterView: CharacterView = id?.let { CharacterView(id = it.toInt(), poster = "", name = "") }
                ?: intent.getParcelableExtra(INTENT_EXTRA_PARAM_CHARACTER)

        return CharacterDetailsFragment.forCharacter(characterView)
    }
}
