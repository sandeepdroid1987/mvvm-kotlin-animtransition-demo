
package com.urban.androidhomework.sample.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urban.androidhomework.sample.UrbanHomeworkApp
import com.urban.androidhomework.sample.core.di.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as UrbanHomeworkApp).appComponent
    }

    @Inject internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}
