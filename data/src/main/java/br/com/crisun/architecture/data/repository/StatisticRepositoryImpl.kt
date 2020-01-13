package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.local.dao.StatisticDao
import br.com.crisun.architecture.data.local.model.StatisticEntity
import br.com.crisun.architecture.domain.model.Statistic
import br.com.crisun.architecture.domain.model.StatisticByHour
import br.com.crisun.architecture.domain.repository.StatisticRepository

class StatisticRepositoryImpl(private val dao: StatisticDao) : StatisticRepository {
    override suspend fun insert(vararg statistics: Statistic) {
        dao.insert(*statistics.map { StatisticEntity(null, it.date) }.toTypedArray())
    }

    override suspend fun getStatistics(): List<StatisticByHour> {
        return dao.getStatistics()
    }
}
