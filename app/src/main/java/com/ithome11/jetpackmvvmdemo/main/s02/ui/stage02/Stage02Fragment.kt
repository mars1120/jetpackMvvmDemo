package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ithome11.jetpackmvvmdemo.R


class Stage02Fragment : Fragment() {

    companion object {
        fun newInstance() = Stage02Fragment()
    }

    private lateinit var viewModel: Stage02ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.stage02_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Stage02ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
