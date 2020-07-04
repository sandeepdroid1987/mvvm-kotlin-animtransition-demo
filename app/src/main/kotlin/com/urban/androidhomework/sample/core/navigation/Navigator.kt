
package com.urban.androidhomework.sample.core.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.urban.androidhomework.sample.features.characters.detail.view.activity.CharacterDetailsActivity
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView
import com.urban.androidhomework.sample.features.characters.list.view.activity.CharactersActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showMain(context: Context) {
        showCharacters(context)
    }

    private fun showCharacters(context: Context) = context.startActivity(CharactersActivity.callingIntent(context))

    fun showCharacterDetails(activity: FragmentActivity, character: CharacterView, navigationExtras: Extras) {
        val intent = CharacterDetailsActivity.callingIntent(activity, character)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    fun openChromeTab(context: Context, url: String) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    class Extras(val transitionSharedElement: View)
}