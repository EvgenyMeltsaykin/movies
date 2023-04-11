package com.cartoonapp.feature.tab_navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cartoonapp.feature.movies.DefaultMoviesComponent
import com.cartoonapp.feature.movies.MoviesDependencies

class DefaultTabNavigationComponent(
    private val dependencies: TabNavigationDependencies,
    componentContext: ComponentContext
) : TabNavigationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, TabNavigationComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(ScreenConfig.Movies) },
        childFactory = ::child,
    )

    override fun onMoviesClicked() {
        navigation.bringToFront(ScreenConfig.Movies)
    }

    override fun onFavoritesClicked() {
        navigation.bringToFront(ScreenConfig.Favorites)
    }

    private fun child(config: ScreenConfig, componentContext: ComponentContext): TabNavigationComponent.Child {
        return when (config) {
            is ScreenConfig.Movies -> {
                TabNavigationComponent.Child.Movies(
                    component = DefaultMoviesComponent(
                        componentContext = componentContext,
                        dependencies = MoviesDependencies(
                            kodikRepository = dependencies.kodikRepository
                        ),
                    )
                )
            }

            is ScreenConfig.Favorites -> {
                TabNavigationComponent.Child.Favorites()
            }
        }
    }

    private sealed interface ScreenConfig : Parcelable {
        @Parcelize
        object Movies : ScreenConfig

        @Parcelize
        object Favorites : ScreenConfig
    }
}