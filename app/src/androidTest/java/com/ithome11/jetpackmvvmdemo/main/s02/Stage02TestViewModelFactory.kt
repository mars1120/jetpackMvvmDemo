package com.ithome11.jetpackmvvmdemo.main.s02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ithome11.jetpackmvvmdemo.main.s02.DI.S02TestViewModelModule
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class Stage02TestViewModelFactory : ViewModelProvider.Factory, KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(S02TestViewModelModule)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val vm by kodein.newInstance {
            Stage02ViewModel(instance()).apply {
                speedOfAnim.value = 10.0f
            }
        }

        return when (modelClass) {
            Stage02ViewModel::class.java -> vm
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
    }

}