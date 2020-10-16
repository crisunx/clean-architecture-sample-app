package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.repository.MessageRepository

class MessageReportUseCaseImpl(private val repository: MessageRepository) : MessageReportUseCase {
    override suspend fun invoke() = repository.getMessageReport()
}
