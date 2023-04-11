package com.cartoonapp.common.core.extensions

import platform.Foundation.NSBundle

fun isConfigurationEnabled(key: String): Boolean {
    return NSBundle.mainBundle.objectForInfoDictionaryKey(key) as String == "YES"
}