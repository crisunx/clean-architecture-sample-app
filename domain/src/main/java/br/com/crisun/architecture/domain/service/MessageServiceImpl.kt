package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesByHour
import br.com.crisun.architecture.domain.repository.MessageRepository

class MessageServiceImpl(private val repository: MessageRepository) : MessageService {
    override suspend fun getMessage() = repository.getMessage()
    override suspend fun insert(vararg messages: Message) = repository.insert(*messages)
    override suspend fun getMessagesByHour(): List<MessagesByHour> = repository.getMessagesByHour()
}
