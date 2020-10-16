package br.com.crisun.architecture.data.network.base

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale("pt", "BR")
private val datFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)

object DateAdapter {
    @FromJson
    fun fromJson(value: String) = datFormat.parse(value)

    @ToJson
    fun toJson(value: Date) = datFormat.format(value)
}
