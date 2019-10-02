package com.ithome11.jetpackmvvmdemo.s03

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ithome11.jetpackmvvmdemo.main.s03.S03ViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class S03ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun add() {
        val viewModel = S03ViewModel()

        // given
        val beforeSize = viewModel.memos.value!!.size
        val givenNewContent = "new content"

        viewModel.newContent.value = givenNewContent

        // when
        viewModel.add()

        // then
        Assert.assertEquals(givenNewContent, viewModel.memos.value!![0].content)
        Assert.assertEquals(beforeSize + 1, viewModel.memos.value!!.size)
    }

    @Test
    fun remove() {
        val viewModel = S03ViewModel()

        // given
        val beforeSize = viewModel.memos.value!!.size
        val givenIdxWillBeRemove = 3
        val beforeMemo = viewModel.memos.value!![givenIdxWillBeRemove]

        // when
        viewModel.remove(viewModel.memos.value!!, givenIdxWillBeRemove)

        // then
        Assert.assertFalse(beforeMemo == viewModel.memos.value!![givenIdxWillBeRemove])
        Assert.assertEquals(beforeSize - 1, viewModel.memos.value!!.size)
    }

}