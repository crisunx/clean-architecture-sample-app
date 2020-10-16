package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.MessagesReport
import br.com.crisun.architecture.domain.repository.MessageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MessageReportUseCaseTest {
    private val repository: MessageRepository = mockk()
    private val useCase by lazy { MessageReportUseCaseImpl(repository) }
    private val messages by lazy { listOf(MessagesReport("21", 10), MessagesReport("22", 45)) }

    @Test
    fun `Calls MessageRepository getMessageReport`() {
        val expected = listOf(MessagesReport("21", 10), MessagesReport("22", 45))

        coEvery { repository.getMessageReport() } returns messages

        val result = runBlocking { useCase() }

        assertEquals(expected, result)
        coVerify { repository.getMessageReport() }
    }
}
