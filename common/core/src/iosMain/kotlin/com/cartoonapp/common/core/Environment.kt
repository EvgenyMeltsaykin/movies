package com.cartoonapp.common.core

import com.cartoonapp.common.core.extensions.isConfigurationEnabled

actual object Environment {
    actual val isDebug: Boolean = Platform.isDebugBinary
    actual val isRelease: Boolean = isDebug.not()
    actual val isStaging: Boolean = isConfigurationEnabled("Staging enabled")
    actual val isMock: Boolean = isConfigurationEnabled("Mock enabled")
}