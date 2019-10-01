package com.ithome11.jetpackmvvmdemo.main.s03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ithome11.jetpackmvvmdemo.main.s03.data.S03DataRepository
import com.ithome11.jetpackmvvmdemo.main.s03.data.S03Memo
import com.ithome11.jetpackmvvmdemo.main.s03.data.S03MemoRepository


class S03ViewModel(private val repository: S03DataRepository<S03Memo> = S03MemoRepository()) :
    ViewModel() {
    sealed class ListAction {
        class Added : ListAction()
        class Removed(val memo: S03Memo, val idx: Int) : ListAction()
    }

    val memos: LiveData<List<S03Memo>> = MutableLiveData<List<S03Memo>>().apply {
        value = repository.list
    }

    val listAction: MutableLiveData<ListAction> = MutableLiveData()
    val newContent: MutableLiveData<String> = MutableLiveData()
    val sizeOfMemos: Int = memos.value!!.size

    fun remove(memo: List<S03Memo>, idx: Int) {
        if (idx < 0) return
        repository.removeAt(idx)
        listAction.value = ListAction.Removed(memo[idx], idx)
    }

    fun add() {
        if (newContent.value == null || newContent.value.equals("")) return
        repository.add(S03Memo(newContent.value ?: ""))
        newContent.value = ""
        listAction.value = ListAction.Added()
    }
}