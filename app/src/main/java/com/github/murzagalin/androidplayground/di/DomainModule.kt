package com.github.murzagalin.androidplayground.di

import com.github.murzagalin.androidplayground.domain.UseCase
import com.github.murzagalin.androidplayground.domain.UseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
class DomainModule {

    @Provides
    @Reusable
    fun provideUseCase(): UseCase {
        return UseCaseImpl()
    }
}