package com.github.murzagalin.androidplayground

import com.github.murzagalin.androidplayground.db.User
import com.github.murzagalin.androidplayground.db.UserDao
import com.github.murzagalin.androidplayground.domain.UseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertIs


class MainViewModelTest {

    private lateinit var useCase: UseCase
    private lateinit var userDao: UserDao
    private val subject: MainViewModel by lazy { MainViewModel(useCase, userDao) }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        useCase = mock()
        userDao = mock()
    }

    @Test
    fun `dataFlow returns a value from the UseCase`() = runTest {
        val testValue = User(
            username = "whatever",
            email = "whatever"
        )
        whenever(userDao.getAllUsers()).thenReturn(listOf(testValue))
        val value = subject.usersFlow.value
        assertIs<MainViewModel.ViewState.Success>(value)
        assertEquals(1, value.users.size)
        assertEquals(testValue, value.users[0])
    }

    @Test
    fun `dataFlow error case`() = runTest {
        val testThrowable = RuntimeException("whatever")
        whenever(userDao.getAllUsers()).thenThrow(testThrowable)
        val value = subject.usersFlow.value

        assertIs<MainViewModel.ViewState.Error>(value)
        assertIs<RuntimeException>(value.error)
        assertEquals(testThrowable.message, value.error.message)
    }
}