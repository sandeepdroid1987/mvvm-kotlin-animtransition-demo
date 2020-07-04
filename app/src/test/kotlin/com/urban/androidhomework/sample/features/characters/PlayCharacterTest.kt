
package com.urban.androidhomework.sample.features.characters

import com.urban.androidhomework.sample.AndroidTest
import com.urban.androidhomework.sample.core.navigation.Navigator
import com.nhaarman.mockitokotlin2.verify
import com.urban.androidhomework.sample.features.characters.detail.usecases.OpenCharacterInfo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class OpenCharacterInfoTest : AndroidTest() {

    private val URL = "https://www.google.com"

    private lateinit var openCharacterInfo: OpenCharacterInfo

    private val context = context()

    @Mock private lateinit var navigator: Navigator

    @Before fun setUp() {
        openCharacterInfo = OpenCharacterInfo(context, navigator)
    }

    @Test fun `should open character url`() {
        val params = OpenCharacterInfo.Params(URL)

        openCharacterInfo(params)

        verify(navigator).openChromeTab(context, URL)
    }
}
