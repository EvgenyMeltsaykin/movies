package com.cartoonapp.feature.movies.mvi

import com.cartoonapp.common.core.mvi.Reducer
import com.cartoonapp.common.core.mvi.RequestState
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import com.cartoonapp.common.core.mvi.lifecycle.LifecycleState
import com.cartoonapp.feature.movies.MoviesState

class MoviesReducer : Reducer<MoviesState, AsyncAction, MoviesAction> {

    override fun reduce(state: MoviesState, action: MoviesAction): Pair<MoviesState, List<AsyncAction>> {
        return when (action) {
            is MoviesAction.LifecycleAction -> handleLifecycleAction(state, action)
            is MoviesAction.Async.TranslationsRequest -> handleTranslationsRequest(state, action)
            is MoviesAction.Input.RefreshClicked -> handleRefreshClicked(state, action)
            is MoviesAction.Input.ShowActionClicked -> handleShowActionClicked(state, action)
            is MoviesAction.Input.TextChanged -> handleTextChanged(state, action)
            is MoviesAction.Async.SearchRequest -> handleSearchRequest(state, action)
            is MoviesAction.Input.ClearActionClicked -> handleClearActionClicked(state, action)
        }
    }

    private fun handleClearActionClicked(
        state: MoviesState,
        action: MoviesAction.Input.ClearActionClicked
    ): Pair<MoviesState, List<MoviesAction.Async>> {
        return state.copy(
            actions = listOf("${System.currentTimeMillis()} ${action}")
        ) to listOf()
    }

    private fun handleSearchRequest(state: MoviesState, action: MoviesAction.Async.SearchRequest): Pair<MoviesState, List<MoviesAction.Async>> {
        return state.copy(
            actions = state.actions + "${System.currentTimeMillis()} ${action}",
            searchRequest = action.request
        ) to listOf()
    }

    private fun handleTextChanged(
        state: MoviesState,
        action: MoviesAction.Input.TextChanged
    ): Pair<MoviesState, List<MoviesAction.Input.TextChanged>> {
        return state.copy(
            actions = state.actions + "${System.currentTimeMillis()} ${action}",
            inputText = action.text,
            searchRequest = if (action.text.isEmpty()) RequestState.Init() else state.searchRequest
        ) to listOf(action)
    }

    private fun handleShowActionClicked(
        state: MoviesState,
        action: MoviesAction.Input.ShowActionClicked
    ): Pair<MoviesState, List<MoviesAction.Async>> {
        return state.copy(
            actions = state.actions + "${System.currentTimeMillis()} ${action}",
            isVisibleAction = !state.isVisibleAction
        ) to listOf()
    }

    private fun handleRefreshClicked(state: MoviesState, action: MoviesAction.Input.RefreshClicked): Pair<MoviesState, List<MoviesAction.Async>> {
        return if (state.isLoading) {
            state.copy(
                actions = state.actions + "${System.currentTimeMillis()} ${action}",
            ) to listOf()
        } else {
            state.copy(
                actions = state.actions + "${System.currentTimeMillis()} ${action}",
            ) to listOf(MoviesAction.Async.TranslationsRequest())
        }
    }

    private fun handleLifecycleAction(state: MoviesState, action: MoviesAction.LifecycleAction): Pair<MoviesState, List<AsyncAction>> {
        return if (action.lifecycleState == LifecycleState.OnCreate) {
            state.copy(
                actions = state.actions + "${System.currentTimeMillis()} ${action}"
            ) to listOf(MoviesAction.Async.TranslationsRequest(), action)
        } else {
            state.copy(
                actions = state.actions + "${System.currentTimeMillis()} ${action}"
            ) to listOf(action)
        }
    }

    private fun handleTranslationsRequest(
        state: MoviesState,
        action: MoviesAction.Async.TranslationsRequest
    ): Pair<MoviesState, List<MoviesAction.Async>> {
        return if (action.request is RequestState.Success) {
            state.copy(
                actions = state.actions + "${System.currentTimeMillis()} ${action}",
                texts = action.request.result.map { it.title },
                request = action.request
            ) to listOf()
        } else state.copy(
            actions = state.actions + "${System.currentTimeMillis()} ${action}",
            request = action.request
        ) to listOf()
    }
}