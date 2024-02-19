package com.example.pswtestapp

import android.app.Application
import com.example.pswtestapp.di.AppComponent
import com.example.pswtestapp.di.DaggerAppComponent
import com.example.pswtestapp.di.modules.DataModule
import com.example.pswtestapp.di.modules.DomainModule

class App: Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .domainModule(DomainModule())
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}