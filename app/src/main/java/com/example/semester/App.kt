package com.example.semester

import android.app.Application
import com.example.semester.di.AppComponent
import com.example.semester.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}