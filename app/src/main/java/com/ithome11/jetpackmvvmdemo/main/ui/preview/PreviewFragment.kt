package com.ithome11.jetpackmvvmdemo.main.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.PreviewFragmentBinding

class PreviewFragment : Fragment() {

    companion object {
        val ARG_PAGE = "PAGE"
        fun newInstance(param1: Int): PreviewFragment {
            val fragment = PreviewFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE, param1)
            fragment.arguments = args
            return fragment
        }
    }


    private val previewViewModel: PreviewViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(PreviewViewModel::class.java)
    }
    private lateinit var mBinding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var binding: PreviewFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.preview_fragment, container, false)
        var rootView: View = binding.root
        // setting values to model
        binding.viewModel = previewViewModel
        mBinding = binding
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        previewViewModel.message = getArguments()?.getInt(ARG_PAGE) ?: 0
        val tv: TextView = activity!!.findViewById(R.id.message)
        tv.setOnClickListener {
            previewViewModel.message++
            mBinding.invalidateAll()
        }
    }
}
