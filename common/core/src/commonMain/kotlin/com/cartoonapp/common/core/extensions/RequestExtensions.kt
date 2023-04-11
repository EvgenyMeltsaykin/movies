package com.cartoonapp.common.core.extensions

import com.cartoonapp.common.core.mvi.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asRequest(): Flow<RequestState<T>> = this
    .map { RequestState.Success(it) as RequestState<T> }
    .onStart { emit(RequestState.Loading()) }
    .catch { emit(RequestState.Error(it)) }