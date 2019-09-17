package com.ithome11.jetpackmvvmdemo


import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * 等liveData回調
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.waitForValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            this@waitForValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return data[0] as T
}
