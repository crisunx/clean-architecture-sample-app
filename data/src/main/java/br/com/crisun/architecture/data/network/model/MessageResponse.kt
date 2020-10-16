package br.com.crisun.architecture.data.network.model

import br.com.crisun.architecture.data.network.base.DomainMapper
import br.com.crisun.architecture.domain.model.Message
import java.util.*

data class MessageResponse(val id: Long, val text: String, val date: Date) : DomainMapper<Message> {
    override fun mapToDomain() = Message(id, text, date)
}
