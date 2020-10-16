package br.com.crisun.cleanarchitecture

import android.app.Application
import br.com.crisun.architecture.data.di.apiModule
import br.com.crisun.architecture.data.di.databaseModule
import br.com.crisun.architecture.data.di.networkModule
import br.com.crisun.architecture.data.di.repositoryModule
import br.com.crisun.architecture.data.networkConfigModule
import br.com.crisun.architecture.domain.di.domainModule
import br.com.crisun.cleanarchitecture.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchitectureApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ArchitectureApplication)
            modules(
                listOf(
                    appModule,
                    apiModule,
                    domainModule,
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    networkConfigModule
                )
            )
        }
    }
}
