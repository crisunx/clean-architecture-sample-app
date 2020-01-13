package br.com.crisun.architecture.data.remote.model

import br.com.crisun.architecture.data.remote.base.DomainMapper
import br.com.crisun.architecture.domain.model.Message

data class MessageResponse(val id: Long, val text: String) : DomainMapper<Message> {
    override fun mapToDomain() = Message(id, text)
}
