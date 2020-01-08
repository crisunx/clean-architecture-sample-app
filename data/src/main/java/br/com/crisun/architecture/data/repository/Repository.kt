package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.network.base.DomainMapper
import br.com.crisun.architecture.domain.model.Error
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class Repository<T : Any, R : DomainMapper<T>> {
    private val generalErrorCode = 666

    protected suspend fun fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                dataProvider()
            } catch (e : Exception) {
                Failure(Error(generalErrorCode, e.message))
            }
        }
    }
}