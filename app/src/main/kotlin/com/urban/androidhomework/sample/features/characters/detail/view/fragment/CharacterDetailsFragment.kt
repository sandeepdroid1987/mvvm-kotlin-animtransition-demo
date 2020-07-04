
package com.urban.androidhomework.sample.features.characters.detail.view.fragment

import android.os.Bundle
import android.view.View
import com.urban.androidhomework.sample.core.platform.BaseFragment
import com.urban.androidhomework.sample.R
import com.urban.androidhomework.sample.features.characters.data.model.CharacterFailure.NonExistentCharacter
import com.urban.androidhomework.sample.core.exception.Failure
import com.urban.androidhomework.sample.core.exception.Failure.NetworkConnection
import com.urban.androidhomework.sample.core.exception.Failure.ServerError
import com.urban.androidhomework.sample.core.extension.close
import com.urban.androidhomework.sample.core.extension.failure
import com.urban.androidhomework.sample.core.extension.isVisible
import com.urban.androidhomework.sample.core.extension.loadFromUrl
import com.urban.androidhomework.sample.core.extension.loadUrlAndPostponeEnterTransition
import com.urban.androidhomework.sample.core.extension.observe
import com.urban.androidhomework.sample.core.extension.viewModel
import com.urban.androidhomework.sample.features.characters.detail.view.animator.CharacterDetailsAnimator
import com.urban.androidhomework.sample.features.characters.detail.view.CharacterDetailsView
import com.urban.androidhomework.sample.features.characters.detail.view.CharacterLocationView
import com.urban.androidhomework.sample.features.characters.list.view.CharacterView
import com.urban.androidhomework.sample.features.characters.detail.viewmodel.CharacterDetailsViewModel
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

class CharacterDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_CHARACTER = "param_character"

        fun forCharacter(character: CharacterView): CharacterDetailsFragment {
            val characterDetailsFragment = CharacterDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_CHARACTER, character)
            characterDetailsFragment.arguments = arguments

            return characterDetailsFragment
        }
    }

    @Inject lateinit var characterDetailsAnimator: CharacterDetailsAnimator

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    override fun layoutId() = R.layout.fragment_character_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { characterDetailsAnimator.postponeEnterTransition(it) }

        characterDetailsViewModel = viewModel(viewModelFactory) {
            observe(characterDetails, ::renderCharacterDetails)
            observe(characterLocation, ::renderCharacterLocation)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            characterDetailsViewModel.loadCharacterDetails((arguments?.get(PARAM_CHARACTER) as CharacterView).id)
        } else {
            characterDetailsAnimator.scaleUpView(characterPlay)
            characterDetailsAnimator.cancelTransition(characterPoster)
            characterPoster.loadFromUrl((arguments!![PARAM_CHARACTER] as CharacterView).poster)
        }
    }

    override fun onBackPressed() {
        characterDetailsAnimator.fadeInvisible(scrollView, characterDetails)
        if (characterPlay.isVisible())
            characterDetailsAnimator.scaleDownView(characterPlay)
        else
            characterDetailsAnimator.cancelTransition(characterPoster)
    }

    private fun renderCharacterDetails(character: CharacterDetailsView?) {
        character?.let {
            with(character) {
                activity?.let {
                    characterPoster.loadUrlAndPostponeEnterTransition(image, it)
                    it.toolbar.title = name
                }
                characterStatus.text = status.ifEmpty { resources.getString(R.string.info_not_available) }
                characterGender.text = gender.ifEmpty { resources.getString(R.string.info_not_available) }
                characterSpecie.text = species.ifEmpty { resources.getString(R.string.info_not_available) }
                characterType.text = type.ifEmpty { resources.getString(R.string.info_not_available) }
                characterPlay.setOnClickListener { characterDetailsViewModel.openCharacterInfo(url) }
                characterDetailsViewModel.loadCharacterLocation(locationId)
            }
        }
        characterDetailsAnimator.fadeVisible(scrollView, characterDetails)
        characterDetailsAnimator.scaleUpView(characterPlay)

    }

    private fun renderCharacterLocation(location: CharacterLocationView?) {
        location?.let {
            with(location) {
                characterLocationName.text = String.format(resources.getString(R.string.character_location_name), it.name)
                characterLocationType.text = String.format(resources.getString(R.string.character_location_type), it.type)
                characterLocationDimension.text = String.format(resources.getString(R.string.character_location_dimension), it.dimension)
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is ServerError -> { notify(R.string.failure_server_error); close() }
            is NonExistentCharacter -> { notify(R.string.failure_character_non_existent); close() }
        }
    }
}
