package br.com.crisun.architecture.data.di

import androidx.room.Room
import br.com.crisun.architecture.data.BuildConfig
import br.com.crisun.architecture.data.database.MessageDatabase
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.network.base.DateAdapter
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
import br.com.crisun.architecture.domain.repository.MessageRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideMoshi() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}

val apiModule = module {
    single { provideApi(get()) }
}

val repositoryModule = module {
    factory<MessageRepository> { MessageRepositoryImpl(get(), get()) }
}

val databaseModule = module {
    single { get<MessageDatabase>().messageDao() }
    single { Room.databaseBuilder(get(), MessageDatabase::class.java, "message.db").build() }
}

fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder().apply {
        readTimeout(60L, TimeUnit.SECONDS)
        connectTimeout(60L, TimeUnit.SECONDS)
        interceptors().addAll(interceptors)
    }.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(BuildConfig.SERVER_BASE_URL)
        client(okHttpClient)
        addConverterFactory(MoshiConverterFactory.create(moshi))
    }.build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(DateAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideApi(retrofit: Retrofit): MessageApi = retrofit.create(MessageApi::class.java)
