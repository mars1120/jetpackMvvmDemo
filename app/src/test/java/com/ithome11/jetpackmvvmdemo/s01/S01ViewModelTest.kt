package com.ithome11.jetpackmvvmdemo.s01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ithome11.jetpackmvvmdemo.main.s01.Stage01ViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class S01ViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy { Stage01ViewModel() }

    private fun setValues(value: String) {
        viewModel.left.value = value
        viewModel.right.value = value
    }

    @Test
    fun plus() {
        // given
        val givenVal = "1"
        setValues(givenVal)

        // when
        viewModel.plus()

        // then
        Assert.assertEquals("2", viewModel.result.value)
    }


    @Test
    fun clear() {
        // given
        val givenVal = "1"
        setValues(givenVal)

        // when
        viewModel.clear()

        // then
        Assert.assertEquals("", viewModel.left.value)
        Assert.assertEquals("", viewModel.right.value)
        Assert.assertEquals("", viewModel.result.value)
    }
}