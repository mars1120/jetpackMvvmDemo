package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

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
import com.ithome11.jetpackmvvmdemo.databinding.Stage02FragmentBinding
import kotlinx.android.synthetic.main.stage02_fragment.*
import java.io.Serializable


class Stage02Fragment : Fragment() {

    private val createVMFactory: () -> ViewModelProvider.Factory by lazy {
        arguments?.getSerializable("createVMFactory") as? () -> ViewModelProvider.Factory
            ?: throw IllegalArgumentException("no createVMFactory for ${this::class.java.simpleName}")
    }
    val viewModel: Stage02ViewModel by lazy {
        ViewModelProviders.of(this, createVMFactory()).get(Stage02ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer {
            lav_result.setAnimation(
                when (it) {
                    Stage02ViewModel.TryResult.FAILED -> R.raw.s02_failed
                    Stage02ViewModel.TryResult.SUCCESS -> R.raw.s02_succes
                    else -> throw IllegalStateException()
                }
            )
            lav_result.playAnimation()
        })

        viewModel.clearAction.observe(this, Observer {
            lav_result.cancelAnimation()
            lav_result.progress = 0.0f
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<Stage02FragmentBinding>(
            inflater,
            R.layout.stage02_fragment,
            container,
            false
        ).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }.root

    companion object {

        @JvmStatic
        fun newInstance(createVMFactory: () -> ViewModelProvider.Factory = ::Stage02ViewModelFactory) =
            Stage02Fragment().apply {
                arguments = Bundle().apply {
                    putSerializable("createVMFactory", createVMFactory as Serializable)
                }
            }
    }

}
