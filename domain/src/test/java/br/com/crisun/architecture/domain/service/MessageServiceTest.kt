package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesByHour
import br.com.crisun.architecture.domain.repository.MessageRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MessageServiceTest {
    private val repository: MessageRepository = mockk()
    private val service by lazy { MessageServiceImpl(repository) }
    private val messages by lazy { listOf(Message(1, "test1"), Message(2, "test2")).toTypedArray() }

    @Test
    fun `Calls MessageRepository getMessage`() {
        coEvery { repository.getMessage() } returns mockk()

        runBlocking { service.getMessage() }

        coVerify { repository.getMessage() }
    }

    @Test
    fun `Calls MessageRepository Insert`() {
        coEvery { repository.insert(*messages) } just Runs

        runBlocking { service.insert(*messages) }

        coVerify { repository.insert(*messages) }
    }

    @Test
    fun `Calls MessageRepository get`() {
        val expectedMessages = listOf(MessagesByHour("21", 10), MessagesByHour("22", 45))

        coEvery { repository.getMessagesByHour() } returns listOf(MessagesByHour("21", 10), MessagesByHour("22", 45))

        val messages = runBlocking { service.getMessagesByHour() }

        Assert.assertEquals(expectedMessages, messages)

        coVerify { repository.getMessagesByHour() }
    }
}
