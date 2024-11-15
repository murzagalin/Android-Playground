package com.github.murzagalin.androidplayground

import android.app.Application
import com.github.murzagalin.androidplayground.di.DaggerApplicationComponent

class MainApplication: Application() {

    val appComponent = DaggerApplicationComponent.create()

}