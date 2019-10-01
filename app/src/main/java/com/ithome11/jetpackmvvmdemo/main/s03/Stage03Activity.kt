package com.ithome11.jetpackmvvmdemo.main.s03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.ActivityStage03Binding
import com.ithome11.jetpackmvvmdemo.main.s03.data.S03Memo
import kotlinx.android.synthetic.main.activity_stage03.*

class Stage03Activity : AppCompatActivity() {

    private val viewModel: S03ViewModel by lazy { ViewModelProviders.of(this).get(S03ViewModel::class.java) }
    private val adapter: androidx.recyclerview.widget.RecyclerView.Adapter<S03MemoAdapter.VH> by lazy { S03MemoAdapter(viewModel.memos.value!!, viewModel::remove) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityStage03Binding>(this, R.layout.activity_stage03).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        viewModel.listAction.observe(this, Observer {
            when(it) {
                is S03ViewModel.ListAction.Added -> onAdded()
                is S03ViewModel.ListAction.Removed -> onRemoved(it.memo, it.idx)
            }
        })

        rcv_contents.let {
            it.adapter = adapter
            it.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        }
    }

    private fun onAdded() {
        adapter.notifyItemInserted(0)
        adapter.notifyItemRangeChanged(1, viewModel.sizeOfMemos -1)
        rcv_contents.post {
            rcv_contents.scrollToPosition(0)
        }
    }

    private fun onRemoved(memo: S03Memo, idx: Int) {
        adapter.notifyItemRemoved(idx)
        adapter.notifyItemRangeChanged(idx, viewModel.sizeOfMemos - idx)
        Toast.makeText(this, "\"${memo.content}\" is removed", Toast.LENGTH_SHORT).show()
    }
}
