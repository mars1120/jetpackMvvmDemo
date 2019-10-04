package com.ithome11.jetpackmvvmdemo.main.s05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.S05FragmentBinding
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModelFactory
import kotlinx.android.synthetic.main.s05_fragment.*
import java.io.Serializable


class S05Fragment : Fragment() {

    private val createVMFactory: () -> ViewModelProvider.Factory by lazy {
        @Suppress("UNCHECKED_CAST")
        arguments?.getSerializable("createVMFactory") as? () -> ViewModelProvider.Factory
            ?: throw IllegalArgumentException("no createVMFactory for ${this::class.java.simpleName}")
    }
    private val viewModel: S05ViewModel by lazy {
        ViewModelProviders.of(this).get(S05ViewModel::class.java)
    }
    private val adapter = S05LogListAdapter()
    val e02ViewModel: Stage02ViewModel by lazy {
        ViewModelProviders.of(this.activity!!, createVMFactory()).get(Stage02ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.logList.observe(this, Observer {
            adapter.submitList(it)
        })



        e02ViewModel.clearAction.observe(this, Observer {
            viewModel.clear()
        })

        fun <T : Any> createObserverForAddingLine(prefix: String): Observer<T> = Observer {
            viewModel.add("$prefix${it.toString()}")
            adapter.notifyItemInserted(adapter.itemCount - 1)
            rcv_log.scrollToPosition(adapter.itemCount - 1)
        }

        e02ViewModel.result.observe(this, createObserverForAddingLine("try result : "))
        e02ViewModel.score.observe(this, createObserverForAddingLine("score : "))

        val setSpeedObserver = createObserverForAddingLine<Int>("set speed  ✕ ")
        viewModel.animSpeed.observe(this, Observer {
            e02ViewModel.speedOfAnim.value = it!!.toFloat()
            setSpeedObserver.onChanged(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<S05FragmentBinding>(
        inflater,
        R.layout.s05_fragment,
        container,
        false
    ).also {
        it.setLifecycleOwner(this)
        it.viewModel = viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_log.adapter = adapter
        rcv_log.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(createVMFactory: () -> ViewModelProvider.Factory = ::Stage02ViewModelFactory) =
            S05Fragment().apply {
                arguments = Bundle().apply {
                    putSerializable("createVMFactory", createVMFactory as Serializable)
                }
            }
    }

}
