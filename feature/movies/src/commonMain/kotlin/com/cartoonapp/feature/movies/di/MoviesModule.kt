package com.cartoonapp.feature.movies.di

import com.cartoonapp.common.core.mvi.Reducer
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.feature.movies.MoviesState
import com.cartoonapp.feature.movies.mvi.MoviesAction
import com.cartoonapp.feature.movies.mvi.MoviesAsyncHandler
import com.cartoonapp.feature.movies.mvi.MoviesReducer
import com.cartoonapp.feature.movies.mvi.MoviesStore
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moviesModule = module {
    factoryOf<Reducer<MoviesState, AsyncAction, MoviesAction>>(::MoviesReducer)
    factory<DslFlowAsyncHandler<AsyncAction, MoviesAction>> { MoviesAsyncHandler(get()) }
    factory { initialState -> MoviesStore(get(), get(), initialState.get()) }
}