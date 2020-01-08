package br.com.crisun.architecture.domain.model

sealed class Result<out T : Any>

data class Failure(val error: Error) : Result<Nothing>()

data class Success<out T : Any>(val data: T) : Result<T>()

data class Error(val code: Int = 0, val message: String?)

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) {
        action(data)
    }
    return this
}

inline fun <T : Any> Result<T>.onFailure(action: (Error) -> Unit) {
    if (this is Failure) {
        action(error)
    }
}
