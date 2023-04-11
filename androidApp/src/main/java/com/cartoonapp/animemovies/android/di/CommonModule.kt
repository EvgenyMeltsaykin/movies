package com.cartoonapp.animemovies.android.di

import com.cartoonapp.animemovies.root.RootDependencies
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformModule = module {

    singleOf(::RootDependencies)
}