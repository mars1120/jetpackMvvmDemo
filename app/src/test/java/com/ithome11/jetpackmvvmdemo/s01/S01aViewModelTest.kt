package com.ithome11.jetpackmvvmdemo.s01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.ithome11.jetpackmvvmdemo.main.s01.Stage01ViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class S01aViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun result_only_when_two_values_changed() {
        val viewModel = Stage01ViewModel()
        val lifecycle = LifecycleRegistry(mockk()).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // given
        val givenLeft = "1"
        val givenRight = "1"

        val visibleResultObserver = mockk<(Boolean?) -> Unit>().also {
            every { it.invoke(false) } returns Unit // for initial value
        }
        viewModel.result.observe({ lifecycle }) {}
        viewModel.visibleResult.observe({ lifecycle }, visibleResultObserver)

        // when
        viewModel.left.value = givenLeft
        // then
        Assert.assertSame(viewModel.result.value, "")

        // when
        every { visibleResultObserver.invoke(true) } returns Unit
        viewModel.right.value = givenRight

        // then
        verify { visibleResultObserver.invoke(true) }
        Assert.assertEquals("2", viewModel.result.value)
    }
}