
package com.urban.androidhomework.sample.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.urban.androidhomework.sample.features.characters.detail.viewmodel.CharacterDetailsViewModel
import com.urban.androidhomework.sample.features.characters.list.viewmodel.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindsCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    abstract fun bindsCharacterDetailsViewModel(characterDetailsViewModel: CharacterDetailsViewModel): ViewModel
}