package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.repository.MessageRepository

class MessageServiceImpl(private val repository: MessageRepository) : MessageService {
    override suspend fun getMessage() = repository.getMessage()
}
