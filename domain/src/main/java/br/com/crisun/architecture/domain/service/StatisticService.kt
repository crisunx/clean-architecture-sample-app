package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Statistic
import br.com.crisun.architecture.domain.model.StatisticByHour

interface StatisticService {
    suspend fun insert(vararg statistics: Statistic)
    suspend fun getStatistics(): List<StatisticByHour>
}
