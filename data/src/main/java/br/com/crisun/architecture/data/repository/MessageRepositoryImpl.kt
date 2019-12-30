package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.api.MessageApi
import br.com.crisun.architecture.data.database.History
import br.com.crisun.architecture.data.database.HistoryDao
import br.com.crisun.architecture.domain.Message
import java.util.*

class MessageRepositoryImpl(private val api: MessageApi, private val dao: HistoryDao) : MessageRepository {
    override suspend fun getMessage() = api.getMessage()

    override suspend fun insert(vararg messages: Message) {
        dao.insert(*messages.map { History(null, Date(), it.text) }.toTypedArray())
    }

    override suspend fun getMessagesByHour() = dao.getMessagesByHour()
}
