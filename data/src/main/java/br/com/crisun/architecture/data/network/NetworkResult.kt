package br.com.crisun.architecture.data.network

import br.com.crisun.architecture.domain.model.Error
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.model.Success
import retrofit2.Response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful)  {
        body()?.run(action)
    }
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (Error) -> Unit) {
    if (!isSuccessful) {
        errorBody()?.run { action(Error(code(), string())) }
    }
}

fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    onSuccess {
        return Success(it.mapToDomainModel())
    }

    onFailure {
        return Failure(it)
    }

    return Failure(Error(1, ""))
}