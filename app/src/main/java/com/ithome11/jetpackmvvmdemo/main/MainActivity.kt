package com.ithome11.jetpackmvvmdemo.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.util.WordListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val wordAdapter = WordListAdapter(this)
        recyclerView.adapter = wordAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    companion object {
        const val newWordActivityRequestCode = 1
    }
}
