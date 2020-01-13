package br.com.crisun.architecture.data.repository

import br.com.crisun.architecture.data.remote.MessageApi
import br.com.crisun.architecture.data.remote.base.getData
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Result
import br.com.crisun.architecture.domain.repository.MessageRepository

class MessageRepositoryImpl(private val api: MessageApi) : Repository<Message>(), MessageRepository {
    override suspend fun getMessage(): Result<Message> {
        return fetchData { api.getMessage().getData() }
    }
}
