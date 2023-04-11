package com.cartoonapp.animemovies.android.features.tabNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.cartoonapp.animemovies.android.features.favorites.FavoritesScreen
import com.cartoonapp.animemovies.android.features.movies.MoviesScreen
import com.cartoonapp.animemovies.root.RootComponent
import com.cartoonapp.feature.tab_navigation.TabNavigationComponent

@Composable
fun TabNavigationScreen(component: TabNavigationComponent, modifier: Modifier = Modifier) {
    val childStack by component.childStack.subscribeAsState()
    val childInstance = childStack.active.instance
    val onTabClick: ((BottomNavTab) -> Unit) = remember(component) {
        { tab ->
            when (tab) {
                BottomNavTab.Movies -> component.onMoviesClicked()
                BottomNavTab.Favorites -> component.onFavoritesClicked()
            }
        }
    }

    val activeTab: BottomNavTab = remember(childInstance) {
        when (childInstance) {
            is TabNavigationComponent.Child.Movies -> BottomNavTab.Movies
            is TabNavigationComponent.Child.Favorites -> BottomNavTab.Favorites
        }
    }

    Scaffold(
        bottomBar = {
            Column(Modifier.zIndex(-1f)) {
                MainBottomNavigation(
                    activeTab = activeTab,
                    onTabClick = onTabClick
                )
            }
        },
        modifier = Modifier.navigationBarsPadding()
    ) { contentPadding ->
        Children(
            stack = childStack,
            modifier = modifier.fillMaxSize(),
        ) {
            when (val child = it.instance) {
                is TabNavigationComponent.Child.Movies -> {
                    MoviesScreen(
                        component = child.component,
                        modifier = Modifier.padding(contentPadding)
                    )
                }
                is TabNavigationComponent.Child.Favorites -> FavoritesScreen(modifier = Modifier.padding(contentPadding))
            }
        }
    }
}

enum class BottomNavTab {
    Movies,
    Favorites
}