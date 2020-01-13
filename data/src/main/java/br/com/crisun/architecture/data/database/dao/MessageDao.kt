package br.com.crisun.architecture.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.crisun.architecture.data.database.model.MessageEntity
import br.com.crisun.architecture.domain.model.MessagesByHour

@Dao
interface MessageDao {
    @Query("DELETE FROM message WHERE date(date) < datetime('now', '-1 days')")
    fun delete()

    @Insert
    suspend fun insert(vararg messageEntities: MessageEntity)

    @Query("SELECT strftime('%H', date) AS hour, count(1) AS total FROM message GROUP BY hour")
    suspend fun getMessagesByHour(): List<MessagesByHour>
}
