package com.ithome11.jetpackmvvmdemo.s02

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class S02ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private fun justTrue() = true

    @Test
    fun tryResult_and_applyScore() {
        val viewModel = Stage02ViewModel(::justTrue)

        // given
        // none

        // when
        viewModel.tryResult()

        // then
        Assert.assertEquals(Stage02ViewModel.TryResult.SUCCESS, viewModel.result.value)

        // given
        // none

        // when
        viewModel.applyScore()

        // then
        Assert.assertEquals(1, viewModel.score.value)
    }

}