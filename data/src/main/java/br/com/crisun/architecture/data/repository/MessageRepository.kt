package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.domain.Message
import br.com.crisun.architecture.domain.MessagesByHour
import br.com.crisun.architecture.domain.model.Result

interface MessageRepository {
    suspend fun getMessage(): Result<Message>
    suspend fun insert(vararg messages: Message)
    suspend fun getMessagesByHour(): List<MessagesByHour>
}
