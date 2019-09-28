package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class Stage02ViewModelFactory : ViewModelProvider.Factory {

    private fun randomBooleanGenerator(): () -> Boolean {
        val r = Random()
        return { r.nextBoolean() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Stage02ViewModel::class.java -> Stage02ViewModel(randomBooleanGenerator())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}