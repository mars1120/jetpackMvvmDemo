package com.ithome11.jetpackmvvmdemo.main.s01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.ActivityStage01Binding

class Stage01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage01)

        val viewModel = ViewModelProviders.of(this).get(Stage01ViewModel::class.java)

        DataBindingUtil.setContentView<ActivityStage01Binding>(this, R.layout.activity_stage01)
            .let {
                it.setLifecycleOwner(this)
                it.viewModel = viewModel
            }

        val setInvisibleResult = Observer<String?> { viewModel.visibleResult.value = false }
        viewModel.left.observe(this, setInvisibleResult)
        viewModel.right.observe(this, setInvisibleResult)
    }
}
