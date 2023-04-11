package com.cartoonapp.common.core.mvi

import com.cartoonapp.common.core.mvi.actions.Action
import com.cartoonapp.common.core.mvi.actions.AsyncAction

interface Reducer<STATE, OUTPUT_ACTION : AsyncAction, INPUT_ACTION : Action> {
    fun reduce(state: STATE, action: INPUT_ACTION): Pair<STATE, List<OUTPUT_ACTION>>
}