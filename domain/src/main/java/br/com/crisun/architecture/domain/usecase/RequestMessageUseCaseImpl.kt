package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.repository.MessageRepository

class RequestMessageUseCaseImpl(private val repository: MessageRepository) : RequestMessageUseCase {
    override suspend fun invoke() = repository.getMessage()
}
