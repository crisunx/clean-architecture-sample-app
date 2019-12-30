package br.com.crisun.architecture.data.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("pt", "BR"))

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { format.parse(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): String? {
        return date?.let { format.format(date) }
    }
}

