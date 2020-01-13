package br.com.crisun.architecture.data.di

import androidx.room.Room
import br.com.crisun.architecture.data.BuildConfig
import br.com.crisun.architecture.data.local.StatisticDatabase
import br.com.crisun.architecture.data.remote.MessageApi
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
import br.com.crisun.architecture.data.repository.StatisticRepositoryImpl
import br.com.crisun.architecture.domain.repository.MessageRepository
import br.com.crisun.architecture.domain.repository.StatisticRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideApi(get()) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    factory<MessageRepository> { MessageRepositoryImpl(get()) }
    factory<StatisticRepository> { StatisticRepositoryImpl(get()) }
}

val databaseModule = module {
    single { get<StatisticDatabase>().statisticDao() }
    single { Room.databaseBuilder(get(), StatisticDatabase::class.java, "statistic.db").build() }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
    connectTimeout(60L, TimeUnit.SECONDS)
    readTimeout(60L, TimeUnit.SECONDS)
}.build()

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
    baseUrl(BuildConfig.SERVER_BASE_URL)
    client(client)
    addConverterFactory(MoshiConverterFactory.create())
}.build()


fun provideApi(retrofit: Retrofit): MessageApi = retrofit.create(MessageApi::class.java)
