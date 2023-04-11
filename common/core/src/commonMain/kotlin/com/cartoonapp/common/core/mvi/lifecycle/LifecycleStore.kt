package com.cartoonapp.common.core.mvi.lifecycle

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction

interface LifecycleStore<ACTION : Action, ASYNC_ACTION : AsyncAction> {
    val asyncHandler: DslFlowAsyncHandler<ASYNC_ACTION, ACTION>
    val lifecycleCallbacks: Lifecycle.Callbacks
    fun registryLifecycle(lifecycle: Lifecycle) {
        lifecycle.subscribe(lifecycleCallbacks)
    }
}
