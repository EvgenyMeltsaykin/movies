package com.cartoonapp.animemovies.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cartoonapp.animemovies.di.createRootModules
import com.cartoonapp.common.core_koin.ComponentKoinContext
import com.cartoonapp.feature.tab_navigation.DefaultTabNavigationComponent

class DefaultRootComponent(
    dependencies: RootDependencies,
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createRootModules(dependencies)
    )

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(ScreenConfig.TabNavigation) },
        childFactory = ::child,
    )


    private fun child(config: ScreenConfig, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is ScreenConfig.TabNavigation -> {
                RootComponent.Child.TabNavigationChild(
                    component = DefaultTabNavigationComponent(
                        componentContext = componentContext,
                        dependencies = scope.get()
                    )
                )
            }
        }
    }


    private sealed interface ScreenConfig : Parcelable {
        @Parcelize
        object TabNavigation : ScreenConfig
    }
}