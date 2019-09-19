package com.ithome11.jetpackmvvmdemo.main

import android.os.SystemClock
import androidx.lifecycle.ViewModel

class ChronometerViewModel : ViewModel() {
    var startTime: Long = SystemClock.elapsedRealtime()
}