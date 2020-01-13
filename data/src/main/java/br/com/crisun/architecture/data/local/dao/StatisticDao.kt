package br.com.crisun.architecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.crisun.architecture.data.local.model.StatisticEntity
import br.com.crisun.architecture.domain.model.StatisticByHour

@Dao
interface StatisticDao {
    @Query("DELETE FROM statistic WHERE date(date) < datetime('now', '-1 days')")
    fun delete()

    @Insert
    suspend fun insert(vararg statisticEntities: StatisticEntity)

    @Query("SELECT strftime('%H', date) AS hour, count(1) AS total FROM statistic GROUP BY hour")
    suspend fun getStatistics(): List<StatisticByHour>
}
