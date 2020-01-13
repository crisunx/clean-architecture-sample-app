package br.com.crisun.architecture.data.remote.base

import br.com.crisun.architecture.domain.model.Error
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.model.Success
import retrofit2.Response

val generalFailure = Failure(Error(666, "Something went wrong, please try again."))

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful)  {
        body()?.run(action)
    }
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (Error) -> Unit) {
    if (!isSuccessful) {
        errorBody()?.use { action(Error(code(), it.string())) }
    }
}

fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    onSuccess {
        return Success(it.mapToDomain())
    }

    onFailure {
        return Failure(it)
    }

    return generalFailure
}
