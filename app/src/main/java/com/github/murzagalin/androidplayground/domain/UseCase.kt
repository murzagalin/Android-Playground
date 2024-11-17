package com.github.murzagalin.androidplayground.domain

import kotlinx.coroutines.delay
import javax.inject.Inject

interface UseCase {

    suspend fun run(): String

}


class UseCaseImpl @Inject constructor(): UseCase {

    override suspend fun run() : String {
        delay(10_000)
        return "Stub value"
    }

}