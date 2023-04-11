package com.cartoonapp.common.core.mvi.actions

import com.cartoonapp.common.core.mvi.lifecycle.LifecycleState

interface MviLifecycleAction : Action {
    val lifecycleState: LifecycleState
}