package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Success
import br.com.crisun.architecture.domain.repository.MessageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MessageServiceTest {
    private val repository: MessageRepository = mockk()
    private val service by lazy { MessageServiceImpl(repository) }

    @Test
    fun `Calls MessageRepository getMessage`() {
        val expected = Success(Message(1, "Test"))

        coEvery { repository.getMessage() } returns Success(Message(1, "Test"))

        val result = runBlocking { service.getMessage() }

        Assert.assertEquals(expected, result)
        coVerify { repository.getMessage() }
    }
}
