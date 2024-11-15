package com.github.murzagalin.androidplayground.di

import com.github.murzagalin.androidplayground.domain.UseCase
import com.github.murzagalin.androidplayground.domain.UseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DomainModule {


    @Binds
    abstract fun bindsUseCase(useCaseImpl: UseCaseImpl): UseCase

}