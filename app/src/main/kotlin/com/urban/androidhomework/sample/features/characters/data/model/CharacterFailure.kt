
package com.urban.androidhomework.sample.features.characters.data.model

import com.urban.androidhomework.sample.core.exception.Failure.FeatureFailure

class CharacterFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentCharacter: FeatureFailure()
}

