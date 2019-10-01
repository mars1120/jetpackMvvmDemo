package com.ithome11.jetpackmvvmdemo.main.s03.data

class S03MemoRepository : S03DataRepository<S03Memo> {

    private val memos by lazy {
        "The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically."
                .split(' ')
                .map(::S03Memo)
                .toMutableList()
    }

    override val list: List<S03Memo> by lazy { memos }

    override fun add(item: S03Memo) {
        memos.add(0, item)
    }

    override fun removeAt(idx: Int) {
        memos.removeAt(idx)
    }

}