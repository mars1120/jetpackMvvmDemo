package com.ithome11.jetpackmvvmdemo.main.s01


import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ithome11.jetpackmvvmdemo.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Stage01ViewModelTest {

    @get:Rule
    val rule = ActivityTestRule<Stage01Activity>(Stage01Activity::class.java)

    private fun plus() {
        Espresso.onView(ViewMatchers.withId(R.id.et_left)).perform(ViewActions.typeText("1"))
        Espresso.onView(ViewMatchers.withId(R.id.et_right)).perform(ViewActions.typeText("1"))
        Espresso.onView(ViewMatchers.withId(R.id.bt_plus)).perform(ViewActions.click())
    }

    @Test
    fun result_should_be_invisible_when_input() {
        // given
        plus() // result view is visible
        Espresso.onView(ViewMatchers.withId(R.id.tv_result)).check(ViewAssertionsEx.isVisibility())

        // when
        Espresso.onView(ViewMatchers.withId(R.id.et_left)).perform(ViewActions.typeText("22"))

        // then
        Espresso.onView(ViewMatchers.withId(R.id.tv_result)).check(ViewAssertionsEx.isInvisible())
    }


}

object ViewAssertionsEx {
    @JvmStatic
    fun isInvisible() = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        Assert.assertEquals(View.INVISIBLE, view.visibility)
    }

    @JvmStatic
    fun isVisibility() = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        Assert.assertEquals(View.VISIBLE, view.visibility)
    }
}