package com.ithome11.jetpackmvvmdemo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _snackBar = MutableLiveData<String>()

    val snackbar: LiveData<String>
        get() = _snackBar



    fun onMainViewClicked() {
        viewModelScope.launch {
            // 暫停線程
            delay(1_000)
            //之後在主線程調用   _snackbar.value
            _snackBar.value = "Hello, from coroutines!"
        }
    }

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }


}