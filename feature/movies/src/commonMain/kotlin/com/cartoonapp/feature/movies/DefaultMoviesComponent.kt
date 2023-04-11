package com.cartoonapp.feature.movies

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.cartoonapp.common.core.feature.CoroutineFeature
import com.cartoonapp.common.core.mvi.RequestState
import com.cartoonapp.common.core_koin.ComponentKoinContext
import com.cartoonapp.feature.movies.di.moviesModule
import com.cartoonapp.feature.movies.mvi.MoviesAction
import com.cartoonapp.feature.movies.mvi.MoviesStore
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class DefaultMoviesComponent(
    componentContext: ComponentContext,
    private val dependencies: MoviesDependencies,
) : MoviesComponent, ComponentContext by componentContext, CoroutineFeature() {
    private val koinContext = instanceKeeper.getOrCreate { ComponentKoinContext() }
    private val scope = koinContext.getOrCreateKoinScope(
        listOf(
            moviesModule,
            module {
                factory { dependencies.kodikRepository }
            }
        )
    )

    private val store = instanceKeeper.getOrCreate {
        scope.get<MoviesStore>(parameters = {
            parametersOf(
                MoviesState(
                    texts = listOf(),
                    request = RequestState.Init(),
                    actions = listOf(),
                    isVisibleAction = false,
                    searchRequest = RequestState.Init()
                )
            )
        })
    }

    init {
        store.registryLifecycle(lifecycle)
    }

    override val stateScreen: StateFlow<MoviesState> = store.observeState()

    override fun refresh() {
        store.emit(MoviesAction.Input.RefreshClicked)
    }

    override fun onActionShowClick() {
        store.emit(MoviesAction.Input.ShowActionClicked)
    }

    override fun onTextChange(text: String) {
        store.emit(MoviesAction.Input.TextChanged(text))
    }

    override fun onClearActionClick() {
        store.emit(MoviesAction.Input.ClearActionClicked)
    }
}
