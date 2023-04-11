package com.cartoonapp.animemovies.android.features.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.cartoonapp.animemovies.android.features.tabNavigation.TabNavigationScreen
import com.cartoonapp.animemovies.root.RootComponent

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.subscribeAsState()

    Children(
        stack = childStack,
        modifier = modifier.fillMaxSize(),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.TabNavigationChild -> TabNavigationScreen(component = child.component)
        }
    }
}