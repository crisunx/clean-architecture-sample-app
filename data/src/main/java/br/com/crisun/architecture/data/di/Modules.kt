package br.com.crisun.architecture.data.di

import androidx.room.Room
import br.com.crisun.architecture.data.BuildConfig
import br.com.crisun.architecture.data.api.MessageApi
import br.com.crisun.architecture.data.database.HistoryDatabase
import br.com.crisun.architecture.data.repository.MessageRepository
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
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
    factory<MessageRepository> { MessageRepositoryImpl(get(), get()) }
}

val databaseModule = module {
    single { get<HistoryDatabase>().historyDao() }
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "history.db").build() }
}

fun provideOkHttpClient() = OkHttpClient.Builder().apply {
    connectTimeout(60L, TimeUnit.SECONDS)
    readTimeout(60L, TimeUnit.SECONDS)
}.build()

fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder().apply {
    baseUrl(BuildConfig.SERVER_BASE_URL)
    client(client)
    addConverterFactory(MoshiConverterFactory.create())
}.build()


fun provideApi(retrofit: Retrofit): MessageApi = retrofit.create(MessageApi::class.java)
