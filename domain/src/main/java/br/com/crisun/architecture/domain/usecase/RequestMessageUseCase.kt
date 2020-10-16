package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Result

interface RequestMessageUseCase {
    suspend operator fun invoke(): Result<Message>
}
