package com.cartoonapp.feature.movies

import com.cartoonapp.common.core.mvi.RequestState
import com.cartoonapp.common.core.mvi.state.MviState
import com.cartoonapp.common.domain.model.kodik.Translations

data class MoviesState(
    val texts: List<String>,
    val request: RequestState<List<Translations>>,
    val searchRequest: RequestState<String>,
    val actions: List<String> = listOf(),
    val isVisibleAction: Boolean,
    val inputText: String = ""
) : MviState {
    val isLoading: Boolean = request is RequestState.Loading
}