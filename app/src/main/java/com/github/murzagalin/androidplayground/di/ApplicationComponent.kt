package com.github.murzagalin.androidplayground.di

import com.github.murzagalin.androidplayground.MainActivity
import dagger.Component


@Component(
    modules = [DomainModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}