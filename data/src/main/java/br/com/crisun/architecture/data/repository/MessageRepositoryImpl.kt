package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.database.dao.MessageDao
import br.com.crisun.architecture.data.database.model.MessageEntity
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.network.base.extractData
import br.com.crisun.architecture.data.network.base.fetchData
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.repository.MessageRepository
import java.util.*

class MessageRepositoryImpl(private val api: MessageApi, private val dao: MessageDao) : MessageRepository {
    override suspend fun getMessage(): Result<Message> {
        return fetchData {
            api.getMessage().extractData()
        }
    }

    override suspend fun insert(vararg messages: Message) {
        dao.insert(*messages.map { MessageEntity(date = it.date, text = it.text) }.toTypedArray())
    }

    override suspend fun getMessageReport() = dao.getMessagesByHour()
}
