package br.com.crisun.architecture.data

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.dsl.module

val networkConfigModule = module {
    single {
        listOf(
            HttpLoggingInterceptor().apply {
                level = BODY
            },
            OkHttpMockInterceptor()
        )
    }
}
