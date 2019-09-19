package com.ithome11.jetpackmvvmdemo.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R

class ChronoActivity : AppCompatActivity() {
    private lateinit var chronometerViewModel: ChronometerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)
        chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> { aLong ->
            val newText = aLong.toString() + " seconds elapsed"
            (findViewById<View>(R.id.text_time) as TextView).setText(newText)
            Log.d("ChronoActivity", "Updating timer")
        }
        chronometerViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}
