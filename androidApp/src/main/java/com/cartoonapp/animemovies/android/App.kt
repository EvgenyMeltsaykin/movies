package com.cartoonapp.animemovies.android

import android.app.Application
import com.cartoonapp.animemovies.android.di.platformModule
import com.cartoonapp.common.core.Environment
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initLogger()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(platformModule)
        }
    }

    private fun initLogger() {
        if (Environment.isRelease) {
            //Napier.base(ReleaseAntilog())
        } else {
            Napier.base(DebugAntilog())
        }
    }
}