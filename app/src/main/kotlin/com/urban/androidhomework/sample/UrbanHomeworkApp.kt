
package com.urban.androidhomework.sample

import android.app.Application
import com.urban.androidhomework.sample.core.di.ApplicationComponent
import com.urban.androidhomework.sample.core.di.ApplicationModule
import com.urban.androidhomework.sample.core.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class UrbanHomeworkApp : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
