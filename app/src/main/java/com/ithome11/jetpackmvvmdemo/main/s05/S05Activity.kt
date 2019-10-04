package com.ithome11.jetpackmvvmdemo.main.s05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02Fragment


class S05Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.s05_activity)
        val s02Fragment = Stage02Fragment.newInstance()
        val s05Fragment = S05Fragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_top, s02Fragment)
            .replace(R.id.fl_bottom, s05Fragment)
            .commit()
    }

}
