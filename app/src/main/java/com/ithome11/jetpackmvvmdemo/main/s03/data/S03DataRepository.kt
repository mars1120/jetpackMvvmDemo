package com.ithome11.jetpackmvvmdemo.main.s03.data

interface S03DataRepository<T> {
    val list: List<T>

    fun add(item: T)
    fun removeAt(idx: Int)
}