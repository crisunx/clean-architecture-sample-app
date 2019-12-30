package br.com.crisun.architecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.crisun.architecture.data.converter.DateConverter

@TypeConverters(DateConverter::class)
@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
