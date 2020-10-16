package br.com.crisun.architecture.domain.repository

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesReport
import br.com.crisun.architecture.domain.model.Result

interface MessageRepository {
    suspend fun getMessage(): Result<Message>
    suspend fun insert(vararg messages: Message)
    suspend fun getMessageReport(): List<MessagesReport>
}
