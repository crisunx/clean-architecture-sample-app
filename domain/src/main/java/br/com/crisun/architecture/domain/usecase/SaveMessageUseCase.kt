package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.Message

interface SaveMessageUseCase {
    suspend operator fun invoke (vararg messages: Message)
}
