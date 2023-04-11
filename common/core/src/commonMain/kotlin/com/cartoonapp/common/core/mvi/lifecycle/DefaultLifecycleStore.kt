package com.cartoonapp.common.core.mvi.lifecycle

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.cartoonapp.common.core.feature.CoroutineFeature
import com.cartoonapp.common.core.mvi.dsl.DslFlowAsyncHandler
import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction
import kotlinx.coroutines.launch

class DefaultLifecycleStore<ACTION : Action, ASYNC_ACTION : AsyncAction>(
    override val asyncHandler: DslFlowAsyncHandler<ASYNC_ACTION, ACTION>,
) : LifecycleStore<ACTION, ASYNC_ACTION>, CoroutineFeature() {
    override val lifecycleCallbacks: Lifecycle.Callbacks = object : Lifecycle.Callbacks {
        override fun onResume() {
            super.onResume()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnResume)
                }
            }
        }

        override fun onCreate() {
            super.onCreate()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnCreate)
                }
            }
        }

        override fun onPause() {
            super.onPause()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnPause)
                }
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnDestroy)
                }
            }
        }

        override fun onStart() {
            super.onStart()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnStart)
                }
            }
        }

        override fun onStop() {
            super.onStop()
            coroutineScope.launch {
                if (asyncHandler is LifecycleAsyncHandler<*, *>) {
                    asyncHandler.emitLifecycleAction(LifecycleState.OnStop)
                }
            }
        }
    }
}