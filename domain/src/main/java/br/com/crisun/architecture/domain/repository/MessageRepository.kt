package br.com.crisun.architecture.domain.repository

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesByHour
import br.com.crisun.architecture.domain.model.Result

interface MessageRepository {
    suspend fun getMessage(): Result<Message>
    suspend fun insert(vararg messages: Message)
    suspend fun getMessagesByHour(): List<MessagesByHour>
}
