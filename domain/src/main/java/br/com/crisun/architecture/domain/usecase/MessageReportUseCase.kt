package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.MessagesReport

interface MessageReportUseCase {
    suspend operator fun invoke(): List<MessagesReport>
}
