
package com.urban.androidhomework.sample.features.characters.detail.usecases

import android.content.Context
import com.urban.androidhomework.sample.features.characters.detail.usecases.OpenCharacterInfo.Params
import com.urban.androidhomework.sample.core.exception.Failure
import com.urban.androidhomework.sample.core.functional.Either
import com.urban.androidhomework.sample.core.functional.Either.Right
import com.urban.androidhomework.sample.core.interactor.UseCase
import com.urban.androidhomework.sample.core.interactor.UseCase.None
import com.urban.androidhomework.sample.core.navigation.Navigator
import javax.inject.Inject

class OpenCharacterInfo
@Inject constructor(private val context: Context,
                    private val navigator: Navigator) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openChromeTab(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}
