package com.cartoonapp.feature.movies.mvi

import com.cartoonapp.common.core.extensions.asRequest
import com.cartoonapp.common.core.extensions.flowAsync
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import com.cartoonapp.common.core.mvi.asyncHandler.MviAsyncHandler
import com.cartoonapp.common.core.mvi.lifecycle.LifecycleAsyncHandler
import com.cartoonapp.common.core.mvi.lifecycle.LifecycleState
import com.cartoonapp.common.domain.repository.KodikRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class MoviesAsyncHandler(
    private val kodikRepository: KodikRepository,
) : MviAsyncHandler<AsyncAction, MoviesAction>(),
    LifecycleAsyncHandler<AsyncAction, MoviesAction> {

    override suspend fun emitLifecycleAction(lifecycleState: LifecycleState) {
        actionStream.emit(MoviesAction.LifecycleAction(lifecycleState))
    }

    override fun transform(eventStream: Flow<AsyncAction>): Flow<MoviesAction> {
        return eventStream.transformations {
            addAll(
                MoviesAction.LifecycleAction::class
                    filter { it.lifecycleState == LifecycleState.OnCreate }
                    react { println("1234 react LifecycleAction ${it.lifecycleState}") },
                MoviesAction.Async.TranslationsRequest::class eventToStream (::handleTranslationsRequest),
                MoviesAction.Input.TextChanged::class streamToStream (::handleTextChanged),
                MoviesAction.Input.TextChanged::class react { println("1234 react TextChanged ${it.text}") },
            )
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun handleTextChanged(eventStream: Flow<MoviesAction.Input.TextChanged>): Flow<MoviesAction> {
        return eventStream
            .debounce(500L)
            .distinctUntilChanged()
            .flatMapLatest {
                flowAsync {
                    delay(2000)
                    "Поиск по ${it.text}"
                }.asRequest().map { MoviesAction.Async.SearchRequest(it) }
            }
    }

    private fun handleTranslationsRequest(action: MoviesAction.Async.TranslationsRequest): Flow<MoviesAction> {
        return flowAsync {
            delay(2000)
            kodikRepository.getTranslations()
        }.asRequest().map {
            MoviesAction.Async.TranslationsRequest(it)
        }
    }
}