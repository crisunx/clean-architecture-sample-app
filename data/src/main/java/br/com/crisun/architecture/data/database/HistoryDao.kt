package br.com.crisun.architecture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.crisun.architecture.domain.MessagesByHour

@Dao
interface HistoryDao {
    @Query("DELETE FROM history WHERE date(date) < datetime('now', '-1 days')")
    fun delete()

    @Insert
    suspend fun insert(vararg histories: History)

    @Query("SELECT strftime('%H', date) AS hour, count(1) AS total FROM history GROUP BY hour")
    suspend fun getMessagesByHour(): List<MessagesByHour>
}
