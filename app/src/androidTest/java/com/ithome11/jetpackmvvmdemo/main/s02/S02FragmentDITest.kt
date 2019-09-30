package com.ithome11.jetpackmvvmdemo.main.s02

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ithome11.jetpackmvvmdemo.main.s02.DI.S02TestViewModelModule
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance


class S02aViewModelTest : KodeinAware {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    override val kodein: Kodein = Kodein.lazy {
        import(S02TestViewModelModule)
    }


    @Test
    fun tryResult_and_applyScore() {
        val viewModel by kodein.newInstance { Stage02ViewModel(instance()) }

        viewModel.tryResult()
        // then
        Assert.assertEquals(Stage02ViewModel.TryResult.SUCCESS, viewModel.result.value)

        // when
        viewModel.applyScore()
        // then
        Assert.assertEquals(1, viewModel.score.value)
    }

}