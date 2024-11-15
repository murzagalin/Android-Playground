package com.github.murzagalin.androidplayground.domain

import javax.inject.Inject

interface UseCase {

    suspend fun run(): String

}

class UseCaseImpl: UseCase {

    override suspend fun run() = "Stub value"

}