package com.ithome11.jetpackmvvmdemo.s02

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.newInstance

val S02DITestViewModelModule = Kodein.Module("S02ViewModel") {
    bind<() -> Boolean>() with singleton { { true } }
}

class S02DIViewModelTest : KodeinAware {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    override val kodein: Kodein = Kodein.lazy {
        import(S02DITestViewModelModule)
    }

    @Test
    fun tryResult_and_applyScore() {
        val viewModel by kodein.newInstance { Stage02ViewModel(instance()) }
        // given
        // none

        // when
        viewModel.tryResult()
        // then
        Assert.assertEquals(Stage02ViewModel.TryResult.SUCCESS, viewModel.result.value)

        // when
        viewModel.applyScore()
        // then
        Assert.assertEquals(1, viewModel.score.value)
    }

}