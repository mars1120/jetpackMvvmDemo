package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ithome11.jetpackmvvmdemo.main.s02.di.S02ViewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class Stage02ViewModelFactory : ViewModelProvider.Factory, KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(S02ViewModelModule)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val vm by kodein.newInstance { Stage02ViewModel(instance()) }

        return when (modelClass) {
            Stage02ViewModel::class.java -> vm
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
    }

}