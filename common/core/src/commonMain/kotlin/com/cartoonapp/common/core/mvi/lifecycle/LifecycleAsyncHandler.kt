package com.cartoonapp.common.core.mvi.lifecycle

import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction

interface LifecycleAsyncHandler<INPUT_ACTION : AsyncAction, OUTPUT_ACTION : Action> : DslFlowAsyncHandler<INPUT_ACTION, OUTPUT_ACTION> {
    suspend fun emitLifecycleAction(lifecycleState: LifecycleState)
}