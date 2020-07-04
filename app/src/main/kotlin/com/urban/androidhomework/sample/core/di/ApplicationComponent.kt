
package com.urban.androidhomework.sample.core.di

import com.urban.androidhomework.sample.UrbanHomeworkApp
import com.urban.androidhomework.sample.core.di.viewmodel.ViewModelModule
import com.urban.androidhomework.sample.features.characters.detail.view.fragment.CharacterDetailsFragment
import com.urban.androidhomework.sample.features.characters.list.view.fragment.CharactersFragment
import com.urban.androidhomework.sample.core.navigation.RouteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: UrbanHomeworkApp)
    fun inject(routeActivity: RouteActivity)

    fun inject(charactersFragment: CharactersFragment)
    fun inject(characterDetailsFragment: CharacterDetailsFragment)
}
