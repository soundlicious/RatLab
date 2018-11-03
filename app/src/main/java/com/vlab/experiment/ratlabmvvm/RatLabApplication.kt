package com.vlab.experiment.ratlabmvvm

import android.app.Application
import com.vlab.experiment.ratlabmvvm.di.appModules
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class RatLabApplication: Application(){

    init {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        startKoin(this, appModules)
    }
}