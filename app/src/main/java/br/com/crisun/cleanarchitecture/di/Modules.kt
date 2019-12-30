package br.com.crisun.cleanarchitecture.di

import androidx.preference.PreferenceManager
import br.com.crisun.cleanarchitecture.ui.MainFragment
import br.com.crisun.cleanarchitecture.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }

    factory { MainFragment() }

    single { PreferenceManager.getDefaultSharedPreferences(get()) }
}
