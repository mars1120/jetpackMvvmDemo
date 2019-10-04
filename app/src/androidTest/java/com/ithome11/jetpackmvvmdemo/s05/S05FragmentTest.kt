package com.ithome11.jetpackmvvmdemo.s05

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.rule.ActivityTestRule
import com.ithome11.jetpackmvvmdemo.FragmentTestActivity
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import com.ithome11.jetpackmvvmdemo.main.s05.S05Fragment
import com.ithome11.jetpackmvvmdemo.util.ViewActionsEx
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class S05FragmentTest {
    @get:Rule
    val rule = ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java)

    lateinit var fragment: S05Fragment

    private fun justTrue() = true

    @Before
    fun setFragment() {

        // given
        val createVMFactory = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T = Stage02ViewModel(::justTrue).apply {
                    speedOfAnim.value = 10.0f
                } as T
            }
        }
        UiThreadStatement.runOnUiThread {
            //            viewModel = E02ViewModel(::justTrue)
            fragment = S05Fragment.newInstance(createVMFactory)
            rule.activity.replaceFragment(fragment)
        }
    }

    @Test
    fun tryResult_should_add_list() {
        // when
        UiThreadStatement.runOnUiThread {
            fragment.e02ViewModel.tryResult()
            fragment.e02ViewModel.applyScore()
        }

        // then
        onView(withId(R.id.rcv_log)).check(ViewAssertionsEx.hasItemCountOfRecyclerView(4))
    }

    @Test
    fun clear_should_clear_list() {
        // when
        UiThreadStatement.runOnUiThread {
            fragment.e02ViewModel.tryResult()
            fragment.e02ViewModel.applyScore()
            fragment.e02ViewModel.clear()
        }

        // then
        onView(withId(R.id.rcv_log)).check(ViewAssertionsEx.hasItemCountOfRecyclerView(1))
    }

    @Test
    fun observing_animSpeed_works() {
        UiThreadStatement.runOnUiThread {
            fragment.e02ViewModel.speedOfAnim.observe(fragment, Observer {})
        }

        // given
        val givenProgress = 2

        // when
        onView(withId(R.id.sb_speed)).perform(ViewActionsEx.setProgress(givenProgress))

        // then
        Assert.assertEquals(3.0f, fragment.e02ViewModel.speedOfAnim.value)
    }

}

object ViewAssertionsEx {
    @JvmStatic
    fun isInvisible() = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        Assert.assertEquals(View.INVISIBLE, view.visibility)
    }

    @JvmStatic
    fun hasItemCountOfRecyclerView(count: Int) = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        val v = view as androidx.recyclerview.widget.RecyclerView
        Assert.assertEquals(count, v.adapter!!.itemCount)
    }
}