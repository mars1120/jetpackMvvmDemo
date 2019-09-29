package com.ithome11.jetpackmvvmdemo

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity

@RestrictTo(RestrictTo.Scope.TESTS)
class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = FrameLayout(this).apply {
            id = R.id.container
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                    , FrameLayout.LayoutParams.MATCH_PARENT)
        }

        setContentView(v)
    }

    inline fun <reified T: androidx.fragment.app.Fragment>replaceFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, f, tag)
                .commit()
    }

    inline fun <reified T: androidx.fragment.app.Fragment>addFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, f, tag)
                .commit()
    }
}
