package com.ithome11.jetpackmvvmdemo.main.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R

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

    private lateinit var viewModel: PreviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PreviewViewModel::class.java)
        val showPage = getArguments()?.getInt(ARG_PAGE) ?: 0
        val tv: TextView = activity!!.findViewById(R.id.message)
        tv.text = "傳遞過來的訊息是:" + showPage.toString()
        tv.setOnClickListener {
            Toast.makeText(context, "tv.text=" + tv.text, Toast.LENGTH_SHORT).show()
        }
    }

}
