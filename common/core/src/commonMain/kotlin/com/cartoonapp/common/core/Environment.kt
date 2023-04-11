package com.cartoonapp.common.core

expect object Environment {
    val isDebug: Boolean
    val isStaging: Boolean
    val isMock: Boolean
    val isRelease: Boolean
}