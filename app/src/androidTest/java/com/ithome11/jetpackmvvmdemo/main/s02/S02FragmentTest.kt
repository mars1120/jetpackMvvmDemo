package com.ithome11.jetpackmvvmdemo.main.s02

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ithome11.jetpackmvvmdemo.FragmentTestActivity
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.main.s02.DI.S02TestFragmentModule
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02Fragment
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein

@RunWith(AndroidJUnit4::class)
class S02FragmentTest {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {}


    @Before
    fun setFragment() {
        val createKodein: () -> Kodein = {
            Kodein.lazy {
                import(S02TestFragmentModule)
            }
        }
        rule.activity.replaceFragment(Stage02Fragment.newInstance(createKodein))
    }


    @Test
    fun score_should_increase_when_success() {
        // when
        Espresso.onView(withId(R.id.bt_try)).perform(ViewActions.click())
        Espresso.onView(isRoot())
            .perform(ViewAssertionsEx.waiting(1000)) // waiting to end animation

        // then
        val expected =
            String.format(rule.activity.resources.getString(R.string.stage02_score_format), 1)
        Espresso.onView(withId(R.id.tv_score)).check(matches(withText(expected)))
    }

    object ViewAssertionsEx {
        @JvmStatic
        fun waiting(milliSec: Long) = object : ViewAction {
            override fun getDescription(): String = "waiting $milliSec milli seconds"

            override fun getConstraints(): Matcher<View> = isDisplayed()

            override fun perform(uiController: UiController, view: View) =
                uiController.loopMainThreadForAtLeast(milliSec)
        }
    }

}