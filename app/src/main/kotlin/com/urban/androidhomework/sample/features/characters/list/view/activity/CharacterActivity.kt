
package com.urban.androidhomework.sample.features.characters.list.view.activity

import android.content.Context
import android.content.Intent
import com.urban.androidhomework.sample.core.platform.BaseActivity
import com.urban.androidhomework.sample.features.characters.list.view.fragment.CharactersFragment

class CharactersActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, CharactersActivity::class.java)
    }

    override fun fragment() = CharactersFragment()
}
