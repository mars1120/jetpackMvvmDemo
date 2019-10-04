package com.ithome11.jetpackmvvmdemo.main.s05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R


class S05Fragment : Fragment() {

    companion object {
        fun newInstance() = S05Fragment()
    }

    private lateinit var viewModel: S05ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.s05_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(S05ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
