package com.github.murzagalin.androidplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.murzagalin.androidplayground.domain.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    companion object {
        private val TAG = this::class.simpleName
    }

    val dataFlow = MutableStateFlow("")

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->

    }

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(errorHandler) {
            val data = withContext(AppDispatchers.Io) {
                useCase.run()
            }

            dataFlow.emit(data)
        }
    }
}