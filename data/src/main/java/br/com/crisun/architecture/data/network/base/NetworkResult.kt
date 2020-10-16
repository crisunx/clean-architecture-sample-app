package br.com.crisun.architecture.data.network.base

import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.model.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

val generalFailure = Failure(Exception())

fun <T : DomainMapper<R>, R : Any> Response<T>.extractData(): Result<R> {
    onSuccess { return Success(it.mapToDomain()) }

    onFailure { return Failure(it) }

    return generalFailure
}

inline fun <T : Any> Response<T>.onFailure(action: (Throwable) -> Unit) {
    if (!isSuccessful) {
        errorBody()?.use { action(Exception()) }
    }
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) {
        body()?.run(action)
    }
    return this
}

suspend fun <T : Any> fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            dataProvider()
        } catch (e: Exception) {
            throw e
        }
    }
}
