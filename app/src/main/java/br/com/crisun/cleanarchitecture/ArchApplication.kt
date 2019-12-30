package br.com.crisun.cleanarchitecture

import android.app.Application
import br.com.crisun.architecture.data.di.databaseModule
import br.com.crisun.architecture.data.di.networkModule
import br.com.crisun.architecture.data.di.repositoryModule
import br.com.crisun.cleanarchitecture.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ArchApplication)
            modules(
                listOf(appModule, networkModule, databaseModule, repositoryModule)
            )
        }
    }
}
