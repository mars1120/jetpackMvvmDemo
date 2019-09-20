package com.ithome11.jetpackmvvmdemo.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.main.ui.preview.PreviewFragment

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PreviewFragment.newInstance(1))
                .commitNow()
        }
    }

}
