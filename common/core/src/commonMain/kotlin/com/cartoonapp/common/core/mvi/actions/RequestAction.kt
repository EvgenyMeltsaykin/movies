package com.cartoonapp.common.core.mvi.actions

import com.cartoonapp.common.core.mvi.RequestState

interface RequestAction<T> : AsyncAction {
    val request: RequestState<T>
}