package com.cartoonapp.animemovies.di

import com.cartoonapp.animemovies.root.RootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun createRootModules(dependencies: RootDependencies): List<Module> {
    return listOf(
        rootModule,
        dataModule,
        module {
        },
    )
}