package br.com.crisun.cleanarchitecture.di

import br.com.crisun.cleanarchitecture.ui.main.MainFragment
import br.com.crisun.cleanarchitecture.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }

    factory { MainFragment() }
}
