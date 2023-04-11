package com.cartoonapp.animemovies.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.cartoonapp.feature.tab_navigation.TabNavigationComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class TabNavigationChild(val component: TabNavigationComponent) : Child()
    }
}
