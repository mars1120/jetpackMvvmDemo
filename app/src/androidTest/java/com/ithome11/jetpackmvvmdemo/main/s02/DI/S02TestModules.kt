package com.ithome11.jetpackmvvmdemo.main.s02.DI

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.main.s02.Stage02TestViewModelFactory
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val S02TestViewModelModule = Kodein.Module("Stage02ViewModel") {
    bind<() -> Boolean>() with singleton { { true } }
}
val S02TestFragmentModule = Kodein.Module("Stage02Fragment") {
    bind<ViewModelProvider.Factory>() with singleton {
        Stage02TestViewModelFactory()
    }
    bind<Stage02ViewModel>() with provider {

        ViewModelProviders.of(context as Fragment, instance()).get(Stage02ViewModel::class.java)
    }
}