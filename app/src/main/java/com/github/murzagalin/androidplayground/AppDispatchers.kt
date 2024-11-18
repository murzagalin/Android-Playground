package com.github.murzagalin.androidplayground

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object AppDispatchers {

    private var _io = Dispatchers.IO

    val Io: CoroutineDispatcher
        get() = _io

    fun overrideIoDispatcher(dispatcher: CoroutineDispatcher) {
        _io = dispatcher
    }
}