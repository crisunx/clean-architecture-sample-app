package br.com.crisun.architecture.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "date") val date: Date?,
    @ColumnInfo(name = "text") val text: String?
)
