package com.cartoonapp.common.core.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <D : Any> flowAsync(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    value: suspend () -> D
): Flow<D> {
    return flow {
        this.emit(value())
    }.flowOn(dispatcher)
}