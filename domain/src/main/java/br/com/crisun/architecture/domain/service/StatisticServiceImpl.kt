package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Statistic
import br.com.crisun.architecture.domain.model.StatisticByHour
import br.com.crisun.architecture.domain.repository.StatisticRepository

class StatisticServiceImpl(private val repository: StatisticRepository) : StatisticService {
    override suspend fun insert(vararg statistics: Statistic) {
        repository.insert(*statistics)
    }

    override suspend fun getStatistics(): List<StatisticByHour> {
        return repository.getStatistics()
    }
}
