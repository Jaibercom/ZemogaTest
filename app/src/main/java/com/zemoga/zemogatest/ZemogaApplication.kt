package com.zemoga.zemogatest

import android.app.Application
import timber.log.Timber


class ZemogaApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
        // Timber init
        Timber.plant(Timber.DebugTree())
    }
}
