package com.cartoonapp.feature.tab_navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.cartoonapp.feature.movies.MoviesComponent

interface TabNavigationComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onMoviesClicked()
    fun onFavoritesClicked()

    sealed class Child {
        class Movies(val component: MoviesComponent) : Child()
        class Favorites() : Child()
    }
}
