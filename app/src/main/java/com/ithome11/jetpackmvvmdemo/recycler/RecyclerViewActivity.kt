package com.ithome11.jetpackmvvmdemo.recycler

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ithome11.jetpackmvvmdemo.R
import kotlinx.android.synthetic.main.activity_recycler_view.*


class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecycler()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycler(){
        recyclerView = rv_parent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity,
                LinearLayout.VERTICAL, false)
            adapter = ParentAdapter(ParentDataFactory
                .getParents(5))
        }

    }
}