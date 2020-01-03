package br.com.crisun.architecture.data.network.model

import br.com.crisun.architecture.data.network.DomainMapper
import br.com.crisun.architecture.domain.Message

data class MessageResponse(val id: Long, val text: String? = "") : DomainMapper<Message> {
    override fun mapToDomainModel() = Message(id, text ?: "")
}