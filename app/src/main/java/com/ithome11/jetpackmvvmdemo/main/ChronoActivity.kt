package com.ithome11.jetpackmvvmdemo.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import kotlinx.android.synthetic.main.activity_chrono.*

class ChronoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)
        val chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        chronometer.base = chronometerViewModel.startTime
        chronometer.start()
    }
}
