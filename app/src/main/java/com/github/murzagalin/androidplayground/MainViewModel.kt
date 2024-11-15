package com.github.murzagalin.androidplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.murzagalin.androidplayground.domain.UseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    companion object {
        private val TAG = this::class.simpleName
    }

    val dataFlow = MutableStateFlow("")

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, "Error loading data: ", throwable)
    }

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO + errorHandler) {
                useCase.run()
            }

            dataFlow.emit(data)
        }
    }
}