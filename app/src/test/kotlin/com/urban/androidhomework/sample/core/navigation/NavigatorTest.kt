
package com.urban.androidhomework.sample.core.navigation

import com.urban.androidhomework.sample.AndroidTest
import com.urban.androidhomework.sample.features.characters.list.view.activity.CharactersActivity
import com.urban.androidhomework.sample.shouldNavigateTo
import org.junit.Before
import org.junit.Test


class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Before fun setup() {
        navigator = Navigator()
    }

    @Test fun `should forward user to characters screen`() {
        navigator.showMain(activityContext())
        RouteActivity::class shouldNavigateTo CharactersActivity::class
    }
}
