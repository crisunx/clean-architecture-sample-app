package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.repository.MessageRepository

class SaveMessageUseCaseImpl(private val repository: MessageRepository) : SaveMessageUseCase {
    override suspend fun invoke(vararg messages: Message) = repository.insert(*messages)
}
