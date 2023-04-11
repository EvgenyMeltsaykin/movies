package com.cartoonapp.common.core.mvi.store

import com.cartoonapp.common.core.mvi.actions.Action
import kotlinx.coroutines.flow.StateFlow

interface Store<ACTION : Action, STATE> {
    fun observeState(): StateFlow<STATE>
    fun emit(action: ACTION)
}