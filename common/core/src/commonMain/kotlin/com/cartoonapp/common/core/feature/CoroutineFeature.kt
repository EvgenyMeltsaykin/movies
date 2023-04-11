package com.cartoonapp.common.core.feature

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.cartoonapp.common.core.extensions.mainCoroutineScope
import kotlinx.coroutines.cancel

abstract class CoroutineFeature : InstanceKeeper.Instance {
    protected val coroutineScope = mainCoroutineScope()

    override fun onDestroy() {
        coroutineScope.cancel()
    }
}