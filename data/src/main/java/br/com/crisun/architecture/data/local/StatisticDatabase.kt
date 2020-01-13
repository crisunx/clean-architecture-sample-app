package br.com.crisun.architecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.crisun.architecture.data.local.dao.StatisticDao
import br.com.crisun.architecture.data.local.model.StatisticEntity

@TypeConverters(Converters::class)
@Database(entities = [StatisticEntity::class], version = 1, exportSchema = false)
abstract class StatisticDatabase : RoomDatabase() {
    abstract fun statisticDao(): StatisticDao
}
