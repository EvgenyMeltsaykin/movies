package com.cartoonapp.feature.movies.mvi

import com.cartoonapp.common.core.mvi.RequestState
import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import com.cartoonapp.common.core.mvi.actions.MviLifecycleAction
import com.cartoonapp.common.core.mvi.actions.RequestAction
import com.cartoonapp.common.core.mvi.lifecycle.LifecycleState
import com.cartoonapp.common.domain.model.kodik.Translations

sealed class MoviesAction : Action {

    data class LifecycleAction(override val lifecycleState: LifecycleState) : MoviesAction(), MviLifecycleAction, AsyncAction

    sealed class Input : MoviesAction() {
        object RefreshClicked : Input()
        object ShowActionClicked : Input()
        object ClearActionClicked : Input()
        data class TextChanged(val text: String) : Input(), AsyncAction
    }

    sealed class Async : MoviesAction(), AsyncAction {
        data class SearchRequest(
            override val request: RequestState<String>
        ) : RequestAction<String>, Async()

        data class TranslationsRequest(
            override val request: RequestState<List<Translations>> = RequestState.Init()
        ) : RequestAction<List<Translations>>, Async()
    }
}