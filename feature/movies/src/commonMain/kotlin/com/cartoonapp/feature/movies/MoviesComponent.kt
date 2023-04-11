package com.cartoonapp.feature.movies

import kotlinx.coroutines.flow.StateFlow

interface MoviesComponent {
    //val childStack: Value<ChildStack<*, Child>>

    val stateScreen: StateFlow<MoviesState>

    fun refresh()
    fun onActionShowClick()
    fun onClearActionClick()
    fun onTextChange(text: String)

    sealed class Child
}
