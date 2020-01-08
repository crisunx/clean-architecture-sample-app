package br.com.crisun.architecture.data.di

import androidx.room.Room
import br.com.crisun.architecture.data.BuildConfig
import br.com.crisun.architecture.data.database.MessageDatabase
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
import br.com.crisun.architecture.domain.repository.MessageRepository
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
    single { get<MessageDatabase>().messageDao() }
    single { Room.databaseBuilder(get(), MessageDatabase::class.java, "message.db").build() }
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
