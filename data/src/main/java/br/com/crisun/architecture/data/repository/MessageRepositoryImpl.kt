package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.database.dao.MessageDao
import br.com.crisun.architecture.data.database.model.MessageEntity
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.network.base.getData
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.repository.MessageRepository
import java.util.*

class MessageRepositoryImpl(private val api: MessageApi, private val dao: MessageDao) : Repository<Message, MessageEntity>(), MessageRepository {
    override suspend fun getMessage(): Result<Message> {
        return fetchData { api.getMessage().getData() }
    }

    override suspend fun insert(vararg messages: Message) {
        dao.insert(*messages.map { MessageEntity(null, Date(), it.text) }.toTypedArray())
    }

    override suspend fun getMessagesByHour() = dao.getMessagesByHour()
}
