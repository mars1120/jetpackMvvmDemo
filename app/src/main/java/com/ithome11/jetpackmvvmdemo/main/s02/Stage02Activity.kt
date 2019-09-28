package com.ithome11.jetpackmvvmdemo.main.s02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02Fragment

class Stage02Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stage02_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Stage02Fragment.newInstance())
                .commitNow()
        }
    }

}
