package com.github.murzagalin.androidplayground

import com.github.murzagalin.androidplayground.domain.UseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals


class MainViewModelTest {

    private lateinit var useCase: UseCase
    private val subject: MainViewModel by lazy { MainViewModel(useCase) }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        useCase = mock()
    }

    @Test
    fun `dataFlow returns a value from the UseCase`() = runTest {
        val testValue = "A test value"
        whenever(useCase.run()).thenReturn(testValue)
        assertEquals(testValue, subject.dataFlow.value)
    }

    @Test
    fun `dataFlow error case`() = runTest {
        whenever(useCase.run()).thenThrow(Throwable("whatever"))
        assertEquals("", subject.dataFlow.value)
    }
}