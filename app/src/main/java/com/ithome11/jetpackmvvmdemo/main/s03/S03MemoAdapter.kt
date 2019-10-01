package com.ithome11.jetpackmvvmdemo.main.s03

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.ListitemS03Binding
import com.ithome11.jetpackmvvmdemo.main.s03.data.S03Memo

typealias OnListItemEvent = (List<S03Memo>, Int) -> Unit

class S03MemoAdapter(private val items: List<S03Memo>, val onClickRemove: OnListItemEvent) : androidx.recyclerview.widget.RecyclerView.Adapter<S03MemoAdapter.VH>() {

    inner class VH(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val content = MutableLiveData<String>()
        
        fun onClickRemove(): Unit = this@S03MemoAdapter.onClickRemove(items, layoutPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val lifecycleOwner = parent.context as LifecycleOwner
        val binding = DataBindingUtil.inflate<ListitemS03Binding>(LayoutInflater.from(parent.context), R.layout.listitem_s03, parent, false)
        val vh = VH(binding.root)

        binding.let {
            it.setLifecycleOwner(lifecycleOwner)
            it.vh = vh
        }

        vh.content.observe(lifecycleOwner, Observer {
            val memo = items[vh.layoutPosition]
            memo.content = it ?: ""
        })
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int): Unit = with(holder) {
        val memo = items[position]
        content.value = memo.content
    }

}