package br.com.crisun.architecture.domain.model

sealed class Result<out T : Any>

data class Failure(val error: Throwable) : Result<Nothing>()

data class Success<out T : Any>(val data: T) : Result<T>()

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) {
        action(data)
    }
    return this
}

inline fun <T : Any> Result<T>.onFailure(action: (Throwable) -> Unit) {
    if (this is Failure) {
        action(error)
    }
}
