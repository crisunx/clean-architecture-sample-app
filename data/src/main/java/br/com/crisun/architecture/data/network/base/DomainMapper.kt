package br.com.crisun.architecture.data.network.base

interface DomainMapper<T : Any> {
    fun mapToDomain(): T
}
