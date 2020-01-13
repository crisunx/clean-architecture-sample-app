package br.com.crisun.architecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.crisun.architecture.data.database.dao.MessageDao
import br.com.crisun.architecture.data.database.model.MessageEntity

@TypeConverters(Converters::class)
@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
