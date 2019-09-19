package com.ithome11.jetpackmvvmdemo.main

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ChronometerViewModel : ViewModel() {
    companion object {
        private val ONE_SECOND = 1000
    }

    private val mElapsedTime = MutableLiveData<Long>()
    private val mInitialTime: Long
    val elapsedTime: LiveData<Long>
        get() = mElapsedTime

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        // 更新經過的時間
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                //通知資料更新
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())

    }
}