package com.github.murzagalin.androidplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.murzagalin.androidplayground.db.User
import com.github.murzagalin.androidplayground.db.UserDao
import com.github.murzagalin.androidplayground.domain.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase,
    private val usersDao: UserDao
): ViewModel() {

    companion object {
        private val TAG = this::class.simpleName
    }

    val usersFlow = MutableStateFlow<ViewState>(ViewState.Empty)

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        usersFlow.tryEmit(ViewState.Error(throwable))
    }

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch(errorHandler) {
            usersFlow.emit(ViewState.Loading)

            val users = withContext(AppDispatchers.Io) {
                usersDao.getAllUsers()
            }

            usersFlow.emit(ViewState.Success(users))
        }
    }

    sealed interface ViewState {

        data object Empty : ViewState

        data object Loading : ViewState

        class Success(val users: List<User>) : ViewState

        class Error(val error: Throwable) : ViewState

    }
}
