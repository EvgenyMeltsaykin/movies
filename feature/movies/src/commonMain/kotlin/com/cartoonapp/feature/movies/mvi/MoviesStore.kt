package com.cartoonapp.feature.movies.mvi

import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.common.core.mvi.Reducer
import com.cartoonapp.common.core.mvi.lifecycle.DefaultLifecycleStore
import com.cartoonapp.common.core.mvi.lifecycle.LifecycleStore
import com.cartoonapp.common.core.mvi.store.MviStatefulStore
import com.cartoonapp.feature.movies.MoviesState

class MoviesStore(
    override val asyncHandler: DslFlowAsyncHandler<MoviesAction.Async, MoviesAction>,
    override val reducer: Reducer<MoviesState, MoviesAction.Async, MoviesAction>,
    initialState: MoviesState
) : MviStatefulStore<MoviesAction, MoviesAction.Async, MoviesState>(initialState),
    LifecycleStore<MoviesAction, MoviesAction.Async> by DefaultLifecycleStore(asyncHandler)