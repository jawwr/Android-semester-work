package com.example.semester.di

import android.content.Context
import com.example.semester.App

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> this.appComponent
        else -> applicationContext.appComponent
    }
