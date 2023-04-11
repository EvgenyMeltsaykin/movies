package com.cartoonapp.common.core.mvi

sealed class RequestState<T> {
    class Init<T> : RequestState<T>()
    class Loading<T>() : RequestState<T>()
    data class Error<T>(val throwable: Throwable) : RequestState<T>()
    data class Success<T>(val result: T) : RequestState<T>()
}

fun <T> RequestState<T>.isLoading(): Boolean = this is RequestState.Loading
fun <T> RequestState<T>.isError(): Boolean = this is RequestState.Error
fun <T> RequestState<T>.isSuccess(): Boolean = this is RequestState.Success
fun <T> RequestState<T>.isInit(): Boolean = this is RequestState.Init