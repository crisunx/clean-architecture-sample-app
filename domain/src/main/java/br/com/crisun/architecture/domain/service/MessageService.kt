package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Result

interface MessageService {
    suspend fun getMessage(): Result<Message>
}
