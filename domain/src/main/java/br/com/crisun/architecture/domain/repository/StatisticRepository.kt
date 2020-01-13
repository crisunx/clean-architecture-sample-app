package br.com.crisun.architecture.domain.repository

import br.com.crisun.architecture.domain.model.Statistic
import br.com.crisun.architecture.domain.model.StatisticByHour

interface StatisticRepository {
    suspend fun insert(vararg statistics: Statistic)
    suspend fun getStatistics(): List<StatisticByHour>
}
