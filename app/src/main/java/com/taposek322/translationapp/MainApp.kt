package com.taposek322.translationapp

import android.app.Application
import android.content.Context
import com.taposek322.translationapp.di.AppComponent
import com.taposek322.translationapp.di.DaggerAppComponent

class MainApp:Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}


val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }