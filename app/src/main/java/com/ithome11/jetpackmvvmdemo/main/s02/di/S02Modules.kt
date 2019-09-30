package com.ithome11.jetpackmvvmdemo.main.s02.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModel
import com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02.Stage02ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import java.util.*

val S02ViewModelModule = Kodein.Module("Stage02ViewModel") {
    bind<() -> Boolean>() with singleton {
        val r = Random()
        val nextBoolean: () -> Boolean = {
            r.nextBoolean()
        }
        nextBoolean
    }
}

val S02FragmentModule = Kodein.Module("Stage02Fragment") {
    bind<ViewModelProvider.Factory>() with singleton {
        Stage02ViewModelFactory()
    }
    bind<Stage02ViewModel>() with provider {
        ViewModelProviders.of(context as Fragment, instance()).get(Stage02ViewModel::class.java)
    }
}