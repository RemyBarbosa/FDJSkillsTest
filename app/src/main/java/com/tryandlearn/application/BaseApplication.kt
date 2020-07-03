package com.tryandlearn.application

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin
import com.tryandlearn.application.di.applicationInjectionsModules

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, applicationInjectionsModules)
        Stetho.initializeWithDefaults(this)
    }
}