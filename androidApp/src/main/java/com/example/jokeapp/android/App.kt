package com.example.jokeapp.android

import android.app.Application
import com.example.jokeapp.ServiceLocator
import com.example.jokeapp.ServiceLocatorImpl

class App : Application() {

    lateinit var serviceLocator: ServiceLocator
        private set

    override fun onCreate() {
        super.onCreate()
        sApplication = this
        serviceLocator = ServiceLocatorImpl()
    }

    companion object {
        private lateinit var sApplication: App

        @JvmStatic
        val instance: App
            get() = sApplication
    }
}
