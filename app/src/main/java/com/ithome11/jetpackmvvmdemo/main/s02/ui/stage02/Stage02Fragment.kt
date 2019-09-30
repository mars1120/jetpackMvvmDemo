package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.Stage02FragmentBinding
import com.ithome11.jetpackmvvmdemo.main.s02.di.S02FragmentModule
import kotlinx.android.synthetic.main.stage02_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.io.Serializable


class Stage02Fragment : Fragment(), KodeinAware {

    override val kodein: Kodein by lazy {
        @Suppress("UNCHECKED_CAST")
        val createKodein = arguments?.getSerializable("createKodein") as? () -> Kodein
            ?: throw IllegalArgumentException("no createKodein for ${this::class.java.simpleName}")
        createKodein()
    }

    private val viewModel: Stage02ViewModel by instance()

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

        private val _createKodein: () -> Kodein by lazy {
            {
                Kodein.lazy { import(S02FragmentModule) }
            }
        }

        @JvmStatic
        fun newInstance(createKodein: () -> Kodein = _createKodein) =
            Stage02Fragment().apply {
                arguments = Bundle().apply {
                    putSerializable("createKodein", createKodein as Serializable)
                }
            }
    }

}
