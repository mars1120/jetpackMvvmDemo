package com.ithome11.jetpackmvvmdemo.main.s05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ithome11.jetpackmvvmdemo.R
import kotlinx.android.synthetic.main.listitem_s05.view.*

class S05LogListAdapter : ListAdapter<String, S05LogListAdapter.VH>(getDiffCallback()) {
    class VH(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listitem_s05, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tv_line.text = getItem(position)
    }

    companion object {
        @JvmStatic
        fun getDiffCallback() = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

}