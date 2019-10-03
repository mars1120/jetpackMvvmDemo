package com.ithome11.jetpackmvvmdemo.s04

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.ithome11.jetpackmvvmdemo.main.s04.S04ViewModel
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class S04ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Test
    fun pickContact() {
        val viewModel = S04ViewModel()
        val lifecycle = LifecycleRegistry(mockk()).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // given
        val givenName = "abc"
        val givenPhone = "111"

        viewModel.contact.observe({ lifecycle }) {}
        viewModel.pickContactAction.observe({ lifecycle }) {
            viewModel.nameAndPhone.value = givenName to givenPhone
        }

        // when
        viewModel.pickContact()

        // then
        Assert.assertEquals("Name : $givenName\nPhone : $givenPhone", viewModel.contact.value)
    }
}