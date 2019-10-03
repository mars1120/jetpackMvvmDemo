package com.ithome11.jetpackmvvmdemo.main.s04

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class S04ViewModel : ViewModel() {

    val nameAndPhone = MutableLiveData<Pair<String, String>>()
    val contact: LiveData<String> = Transformations.map(nameAndPhone) { (name, phone) ->
        "Name : $name\nPhone : $phone"
    }
    val pickContactAction = MutableLiveData<Unit>()

    fun pickContact() {
        pickContactAction.value = Unit
    }
}