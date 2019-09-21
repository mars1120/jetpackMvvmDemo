package com.ithome11.jetpackmvvmdemo.main.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreviewViewModel : ViewModel() {
    private val _message = MutableLiveData(0)
    val message: LiveData<Int> = _message
    fun onUpdateMessage(int: Int) {
        _message.value = int
    }

    fun onClick() {
        _message.value = _message.value!! + 1
    }
}
