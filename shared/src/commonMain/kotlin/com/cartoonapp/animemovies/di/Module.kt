package com.cartoonapp.animemovies.di

import com.cartoonapp.feature.tab_navigation.TabNavigationDependencies
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val rootModule = module {
    singleOf(::TabNavigationDependencies)
}