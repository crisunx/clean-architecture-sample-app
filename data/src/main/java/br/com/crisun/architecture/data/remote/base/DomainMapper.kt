package br.com.crisun.architecture.data.remote.base

interface DomainMapper<T : Any> {
    fun mapToDomain(): T
}
