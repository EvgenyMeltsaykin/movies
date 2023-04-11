package com.cartoonapp.common.core.mvi

import com.cartoonapp.common.core.mvi.actions.Action
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ActionStream<ACTION : Action> {
    private val stream = MutableSharedFlow<ACTION>()

    fun observe() = stream.asSharedFlow()

    suspend fun emit(action: ACTION) {
        stream.emit(action)
    }
}