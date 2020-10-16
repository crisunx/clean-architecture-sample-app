package br.com.crisun.architecture.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.crisun.architecture.data.network.base.DomainMapper
import br.com.crisun.architecture.domain.model.Message
import java.util.*

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name = "text")
    val text: String
) : DomainMapper<Message> {
    override fun mapToDomain() = Message(id = id, text = text, date = date)
}
