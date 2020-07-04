
package com.urban.androidhomework.sample.features.characters.list.view.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.urban.androidhomework.sample.core.platform.BaseFragment
import com.urban.androidhomework.sample.R
import com.urban.androidhomework.sample.features.characters.data.model.CharacterFailure.ListNotAvailable
import com.urban.androidhomework.sample.core.exception.Failure
import com.urban.androidhomework.sample.core.exception.Failure.NetworkConnection
import com.urban.androidhomework.sample.core.exception.Failure.ServerError
import com.urban.androidhomework.sample.core.extension.failure
import com.urban.androidhomework.sample.core.extension.invisible
import com.urban.androidhomework.sample.core.extension.observe
import com.urban.androidhomework.sample.core.extension.viewModel
import com.urban.androidhomework.sample.core.extension.visible
import com.urban.androidhomework.sample.core.navigation.Navigator
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView
import com.urban.androidhomework.sample.features.characters.list.view.adapter.CharactersAdapter
import com.urban.androidhomework.sample.features.characters.list.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.emptyView
import kotlinx.android.synthetic.main.fragment_characters.characterList
import javax.inject.Inject

class CharactersFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var charactersAdapter: CharactersAdapter

    private lateinit var charactersViewModel: CharactersViewModel

    override fun layoutId() = R.layout.fragment_characters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        charactersViewModel = viewModel(viewModelFactory) {
            observe(characters, ::renderCharactersList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadCharactersList()
    }


    private fun initializeView() {
        characterList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        characterList.adapter = charactersAdapter
        charactersAdapter.clickListener = { character, navigationExtras ->
                    navigator.showCharacterDetails(activity!!, character, navigationExtras) }
    }

    private fun loadCharactersList() {
        emptyView.invisible()
        characterList.visible()
        showProgress()
        charactersViewModel.loadCharacters()
    }

    private fun renderCharactersList(characters: List<CharacterView>?) {
        charactersAdapter.collection = characters.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_characters_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        characterList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadCharactersList)
    }
}
