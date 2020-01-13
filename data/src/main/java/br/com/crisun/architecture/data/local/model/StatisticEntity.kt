package br.com.crisun.architecture.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.crisun.architecture.data.remote.base.DomainMapper
import br.com.crisun.architecture.domain.model.Statistic
import java.util.*

@Entity(tableName = "statistic")
data class StatisticEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "date") val date: Date?
) : DomainMapper<Statistic> {
    override fun mapToDomain() = Statistic(id ?: 0, date?: Date())
}
