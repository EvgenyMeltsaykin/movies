package com.cartoonapp.common.core.mvi.store

import com.cartoonapp.common.core.feature.CoroutineFeature
import com.cartoonapp.common.core.mvi.ActionStream
import com.cartoonapp.common.core.mvi.Reducer
import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.common.core.mvi.state.MviState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

abstract class MviStatefulStore<ACTION : Action, ASYNC_ACTION : AsyncAction, STATE : MviState>(initialState: STATE) :
    Store<ACTION, STATE>, CoroutineFeature() {

    private val state = MutableStateFlow(initialState)
    private val actionStream: ActionStream<ACTION> = ActionStream()
    private val asyncActionStream: ActionStream<ASYNC_ACTION> = ActionStream()
    abstract val reducer: Reducer<STATE, ASYNC_ACTION, ACTION>
    abstract val asyncHandler: DslFlowAsyncHandler<ASYNC_ACTION, ACTION>

    init {
        coroutineScope.launch {
            val asyncStream = asyncActionStream.observe().shareIn(coroutineScope, SharingStarted.Eagerly)
            actionStream.observe()
                .onEach { action ->
                    val (newState, newAsyncEvents) = reducer.reduce(state = state.value, action = action)
                    state.value = newState
                    newAsyncEvents.forEach { asyncActionStream.emit(it) }
                }
                .shareIn(coroutineScope, SharingStarted.Eagerly)
            asyncHandler.asyncActionStreamListener(asyncStream)
            asyncHandler.observeActionStream().collect { emit(it) }
        }
    }

    override fun emit(action: ACTION) {
        coroutineScope.launch { actionStream.emit(action) }
    }

    override fun observeState(): StateFlow<STATE> {
        return state.asStateFlow()
    }
}