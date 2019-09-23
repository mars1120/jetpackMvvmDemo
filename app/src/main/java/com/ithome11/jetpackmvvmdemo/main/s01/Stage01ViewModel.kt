package com.ithome11.jetpackmvvmdemo.main.s01

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Stage01ViewModel : ViewModel() {
    val left = MutableLiveData<String>("")
    val right = MutableLiveData<String>("")
    val result = MediatorLiveData<String>().apply {
        value = ""
        val plus: (String?) -> Unit = plus@{
            val leftNum = left.value?.toIntOrNull()
            if (leftNum == null) {
                visibleResult.value = false
                return@plus
            }
            val rightNum = right.value?.toIntOrNull()
            if (rightNum == null) {
                visibleResult.value = false
                return@plus
            }
            value = (leftNum + rightNum).toString()
            visibleResult.value = true
        }
        addSource(left, plus)
        addSource(right, plus)
    }

    val visibleResult = MutableLiveData<Boolean>().apply { value = false }

    fun plus() {
        val leftNum =
            left.value.toString().toIntOrNull() ?: run { visibleResult.value = false; return }
        val rightNum =
            right.value.toString().toIntOrNull() ?: run { visibleResult.value = false; return }
        result.value = (leftNum + rightNum).toString()
        visibleResult.value = true
    }

    fun clear() {
        left.value = ""
        right.value = ""
        result.value = ""
        visibleResult.value = false
    }
}