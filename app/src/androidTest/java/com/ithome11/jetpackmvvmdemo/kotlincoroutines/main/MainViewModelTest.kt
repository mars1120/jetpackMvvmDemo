package com.example.android.kotlincoroutines.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.ithome11.jetpackmvvmdemo.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {


    //將liveData調轉到主線程
    @get:Rule
    val instantTaskExecutorRu = InstantTaskExecutorRule()

    var testDispatcher = TestCoroutineDispatcher()

    lateinit var subject: MainViewModel

    /**
     * 初始化
     */
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        subject = MainViewModel()
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun whenMainViewModelClicked_showSnackbar() = testDispatcher.runBlockingTest {
        subject.onMainViewClicked()
        advanceTimeBy(1_000)
        Truth.assertThat(subject.snackbar.value)
            .isEqualTo("Hello, from coroutines!")
    }

}

